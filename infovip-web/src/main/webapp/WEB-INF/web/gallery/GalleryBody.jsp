<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/tlds/module-manager" prefix="module"%>
<%@ taglib uri="/tlds/translate" prefix="tr"%>



<div class="page--header pt--60 pb--60 text-center bg--img" data-overlay="0.85" data-rjs="2" style="">
            <div class="container">
                <div class="title">
                    <h2 class="h1 text-white">Public Gallery</h2>
                </div>

                <ul class="breadcrumb text-gray ff--primary">
                </ul>
            </div>
</div>


<section class="gallery-list">

			<div vue-component="init">
					<gallery-waterfall-component  
						waterfall-id="gallery-data" 
						source-url="/gallery/data"
					></gallery-waterfall-component>
			</div>

</section>

<script type="text/x-handlebars-template" id="waterfall-tpl">                                                                                                                                                    
 {{#result}}                
	<div class="gallery item-picture">

	</div>                               
 {{/result}}                                                                                                                                                                                                      
</script>                                                                                                                                                                                                        


