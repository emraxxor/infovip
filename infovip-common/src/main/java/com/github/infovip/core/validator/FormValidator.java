package com.github.infovip.core.validator;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.log4j.Logger;
import org.apache.tika.Tika;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Mode;

import com.github.infovip.core.exceptions.InvalidImageDataException;
import com.github.infovip.core.lang.Translate;
import com.github.infovip.core.web.response.ValidationResponse;
import com.github.infovip.core.web.types.FormData;
import com.github.infovip.core.web.types.ValidationType;
import com.github.infovip.core.web.validation.AllowEmpty;
import com.github.infovip.core.web.validation.MaxLength;
import com.github.infovip.core.web.validation.NotEmpty;
import com.github.infovip.core.web.validation.Numeric;
import com.github.infovip.core.web.validation.ValidEmail;


/**
 * 
 * @author Attila Barna
 * @category infovip.form
 *
 * @param <T>
 */
public abstract class FormValidator<T extends FormData> {

	protected T data;

	protected Logger logger = Logger.getLogger(FormValidator.class);

	protected List<ValidationResponse> responses;

	protected Class<?> classz;

	public FormValidator(T data) {
		this.data = data;
		this.classz = data.getClass();
		this.responses = new ArrayList<>();
	}

	public boolean isValid() {
		for(ValidationResponse r : responses) {
			if ( r.getValidationType() != ValidationType.VALIDATION_SUCCESSFUL ) {
				return false;
			}
		}
		
		return true;
	}
	
	public String convertImage(String image, Mode mode, int targetSize ) throws IOException, InvalidImageDataException {
		String[] parts = image.split(",");
		String imageString = parts[1];
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ByteArrayInputStream bis = new ByteArrayInputStream(Base64.decodeBase64(imageString));
		BufferedImage img = ImageIO.read(bis);
		
		
		String contentType = new Tika().detect(Base64.decodeBase64(imageString));

		if ( contentType.indexOf("image") == -1 )
			throw new InvalidImageDataException("Unsupported image data.");

		
		BufferedImage bi = Scalr.resize(img, mode, targetSize) ;
	    ImageIO.write(bi, "png", bos);
	    String result = Base64.encodeBase64String(bos.toByteArray());
	    bis.close();
	    bos.close();
	    return result;
	}
	
	
	public String convertImage(String image, int targetSize) throws IOException, InvalidImageDataException {
		return convertImage(image, Mode.FIT_TO_WIDTH, targetSize);
	}

	
	public boolean validate() {
		boolean result = true;

		Field[] fields = classz.getDeclaredFields();
		for (Field f : fields) {
			try {
				if (f.getAnnotation(NotEmpty.class) != null) {
					Object value = classz.getMethod("get" + StringUtils.capitalize(f.getName())).invoke(data);
					if (  value != null &&  value instanceof String && ((String) value).length() == 0) {
						addResponse(new ValidationResponse(ValidationType.EMPTY_FIELD, Translate.tr("validation.msg.field.empty"), f.getName()));
						result = false;
					} else if ( value == null ) {
						addResponse(new ValidationResponse(ValidationType.EMPTY_FIELD, Translate.tr("validation.msg.field.empty"), f.getName()));
						result = false;
					}
				} else if ( f.getAnnotation(ValidEmail.class) != null ) {
					String value = (String) classz.getMethod("get" + StringUtils.capitalize(f.getName())).invoke(data);
					if ( !EmailValidator.getInstance().isValid( value ) ) {
						addResponse(new ValidationResponse(ValidationType.EMAIL_VALIDATION_ERROR, Translate.tr("validation.msg.email.invalid"), f.getName()));
						result = false;
					}
				} else if ( f.getAnnotation(Numeric.class) != null ) {
					if ( f.getAnnotation(AllowEmpty.class) != null ) continue;
					
					String value = (String) classz.getMethod("get" + StringUtils.capitalize(f.getName())).invoke(data);
					if ( !StringUtils.isNumeric(value) ) {
						addResponse(new ValidationResponse(ValidationType.VALIDATION_NUMERIC_ERROR, Translate.tr("validation.msg.field.numeric"), f.getName()));
						result = false;
					}
				} else if ( f.getAnnotation(MaxLength.class) != null ) {
					if ( f.getAnnotation(AllowEmpty.class) != null ) continue;
					
					String value = (String) classz.getMethod("get" + StringUtils.capitalize(f.getName())).invoke(data);
					if ( value.length() > f.getAnnotation(MaxLength.class).size() ) {
						addResponse(new ValidationResponse(ValidationType.VALIDATION_MAX_LENGTH, Translate.tr("validation.msg.field.max.length"), f.getName()));
						result = false;
					}
				}

			} catch (IllegalAccessException e) {
				logger.error(e);
				addResponse(new ValidationResponse(ValidationType.EMPTY_FIELD,
						Translate.tr("validation.unexpected.error")));
				result = false;
			} catch (IllegalArgumentException e) {
				logger.error(e);
				addResponse(new ValidationResponse(ValidationType.EMPTY_FIELD,
						Translate.tr("validation.unexpected.error")));
				result = false;
			} catch (InvocationTargetException e) {
				logger.error(e);
				addResponse(new ValidationResponse(ValidationType.EMPTY_FIELD,
						Translate.tr("validation.unexpected.error")));
				result = false;
			} catch (NoSuchMethodException e) {
				logger.error(e);
				addResponse(new ValidationResponse(ValidationType.EMPTY_FIELD,
						Translate.tr("validation.unexpected.error")));
				result = false;
			} catch (SecurityException e) {
				logger.error(e);
				addResponse(new ValidationResponse(ValidationType.EMPTY_FIELD,
						Translate.tr("validation.unexpected.error")));
				result = false;
			}
		}

		if (result == true) 
			addResponse(new ValidationResponse(ValidationType.VALIDATION_SUCCESSFUL, Translate.tr("validation.successfull")));

		return result;
	}

	public final List<ValidationResponse> responses() {
		return this.responses;
	}

	protected final void addResponse(ValidationResponse resp) {
		this.responses.add(resp);
	}

	protected final void removeResponse(ValidationResponse resp) {
		this.responses.remove(resp);
	}
}
