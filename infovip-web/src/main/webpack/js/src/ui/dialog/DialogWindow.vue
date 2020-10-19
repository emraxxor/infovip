<template>
    <div :class="classes" role="dialog">
        <div class="modal-dialog" :style="dialogStyle">
                <div class="modal-content">
            
                    <div class="modal-header">
                        <h5 class="modal-title">{{ title }}</h5>
                        <input @click="close()" type="button" value="X" btn-model="CLOSE" class="btn btn-dialog-close" style="position: absolute;top:0;right:0;">
                    </div>
                
                    <div class="modal-body modal-mobile-body">
                            <slot name="dialogBody"></slot>
                    </div>

                    <slot name="dialogFooter"></slot>

                </div>
            
        </div>
    </div>
</template>
<script>
import DialogBody from './DialogBody';


export default {
    data() {
         return {
            classes : [
                'modal',
                'fake'
            ],
            dialogStyle : {
                top : this.marginTop,
                minWidth : Math.floor(window.screen.width * (this.width/100)) + 'px',
                minHeight : Math.floor(window.screen.width * (this.height/100)) + 'px',
            },
        }
    },

    computed : {
  
    },

    props: {
        width: {
            default : 80,
        },
        height : {
            default:  80,
        },
        marginTop : {
            default : 0,
        },
        title : {
            type: String,
            required: true
        },
        height : {
            default : 80,
        },
        width : {
            default : 80,
        },
        open : {
            default : false,
            required:  true,
        },
        onClose : {
            type: Function,
            required : true,
        }
    },

    methods : {
        close : function() {
            this.onClose(this);
        },

        setWidth(v) {
            this.dialogStyle.minWidth = Math.floor(window.screen.width * (v/100)) + 'px';
        },

        setHeight(v) {
            this.dialogStyle.minHeight = Math.floor(window.screen.height * (v/100)) + 'px';
        }
    },


    watch : {
    

        open : function(v) {
            if ( v ) {
                this.classes.push('show');
            } else {
                this.classes = this.classes.filter(e => e !== 'show');
            }
        }
    }
    
}
</script>