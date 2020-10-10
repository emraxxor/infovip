/**
 * @author Attila Barna
 * @deprecated  
 */
class PhotoCommentBox extends UIController {

	constructor(dialog) {
		super();
		this.dialog = dialog;
		this.editor = document.createElement('textarea');
		this.commentbox = this.dialog.getBody().querySelector(".media--comments");
		this.submitBtn = document.createElement('button');
		this.submitBtn.type = 'button';
		this.submitBtn.classList.add('btn');
		this.submitBtn.classList.add('btn-primary');
		this.submitBtn.classList.add('m-auto');
		//this.submitBtn.style.marginTop = '5% !important';
		this.submitBtn.setAttribute('style','margin-top:5% !important;')
		this.submitBtn.append('Send');
		this.submitBtn.addEventListener('click', e => this.onClickSubmit(e) );
	}
	
	onClickSubmit(e) {
		var that = this;
		if ( this.editor.value != null ) {
			let data = {
				comment : this.editor.value	,
				mediaId : this.dialog.data.mediaId,
				photoId : this.dialog.data.documentId
			};
			
			const result = function(result, ob ) {
				if ( result[0] != undefined && result[0]['type'] != undefined ) {
					DefaultFormValidatorHandlerDialog(result).display();	
				} else {
					const commentElement = jQuery( Mustache.render( 
							   that.load(ApplicationScope.config.RESOURCES_PATH + '/mst/media/PhotoMediaComment.mst', {}, 'GET') , 
							   {
									dateFormat : function() {
											return function(text,render) {
												return moment(render(text)).format("YYYY-MM-DD HH:mm:ss" );
											}
									},
									comment :  result
								} 
					) );
					
					that.commentbox.prepend(commentElement[0]);
					that.editor.value = "";
				}
			}
			
			this.async('/user/photo/comment', data, result, this );
			
		}
	}
	
	render() {
		this.commentbox.prepend(this.submitBtn);
		this.commentbox.prepend(this.editor);
	}
	
	onCreationComplete() {
	}
}