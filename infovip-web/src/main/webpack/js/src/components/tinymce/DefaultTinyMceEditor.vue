<template>
    <div ref="layer">
        <textarea :id="nodeId" ref="editor"></textarea>
    </div>
</template>

<script>

export default {

        props: {
            nodeId : {
                default: () => Math.random().toString(36).substr(2)
            },
            handler: {
                default: null,
            },
            width: {
                default: '100%' 
            },
            height: {
                default: '300'
            },
            basic : {
                default: true,
            },
            plugins : {
                
                default: () => [
                  	"advlist autolink link image lists charmap print preview hr anchor pagebreak",
  					"searchreplace wordcount visualblocks visualchars code fullscreen insertdatetime media nonbreaking",
  					"table contextmenu directionality emoticons template textcolor paste textcolor colorpicker textpattern"
                ]
            },
            toolbar : {
                default: () => [
                    "newdocument | bold italic underline strikethrough | alignleft aligncenter alignright alignjustify | styleselect formatselect fontselect fontsizeselect",
                    "cut copy paste | searchreplace | bullist numlist | outdent indent blockquote | undo redo | link unlink anchor image media code | insertdatetime preview | forecolor backcolor",
                    "table | hr removeformat | subscript superscript | charmap emoticons | print fullscreen | ltr rtl | visualchars visualblocks nonbreaking template pagebreak restoredraft",
                ]
            }
        },

        computed: {
        },



        mounted () {     	

            if ( this.basic ) {
                tinyMCE.init({
     				  target : this.$refs.editor,
     				  menubar: false,
     				  plugins: [
     				    'advlist autolink lists link image charmap print preview anchor textcolor',
     				    'searchreplace visualblocks code fullscreen',
     				    'insertdatetime media table contextmenu paste code help wordcount'
     				  ],
     				  toolbar: 'insert | undo redo |  formatselect | bold italic backcolor  | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | removeformat | help',
 					  doctype: '',
 					  width: this.width,
 					  height: this.height,
 					  relative_urls : false,
 					  remove_script_host : false,
 					  convert_urls : true,
 					  images_upload_url: this.handler,
 					  images_upload_handler: function (blobInfo, success, failure) {
                            this.$emit('imageUploaded', {blobInfo,success,failure});
 					  }
     		  });

            } else {
                tinyMCE.init({
                            target : this.$refs.editor,
                            plugins: this.plugins,
                            toolbar1: this.toolbar[0],
                            toolbar2: this.toolbar[1],
                            toolbar3: this.toolbar[2],
                            language: 'hu_HU',
                            doctype: '',
                            width: this.width,
                            height: this.height,
                            relative_urls : false,
                            remove_script_host : false,
                            convert_urls : true,
                            images_upload_url: this.handler,
                            images_upload_handler: function (blobInfo, success, failure) {
                                    this.$emit('imageUploaded', {blobInfo,success,failure});
                            }
                    });
            }
        },

   
        methods: {
            clear() {
                tinyMCE.get(this.nodeId).setContent('');
            },

            setText : function(v) {
                tinyMCE.get(this.nodeId).setContent(v);
            },

            getText: function() {
                return tinyMCE.get(this.nodeId).getContent();
            }
        },

}
</script>