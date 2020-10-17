<template>
    <div class="modal fade show" role="dialog">
        <div class="modal-dialog" style="top:30%">
                <div class="modal-content">
            
                    <div class="modal-header">
                        <h5 class="modal-title">{{ title }}</h5>
                        <input type="button" value="X" btn-model="CLOSE" class="btn btn-dialog-close" style="position: absolute;top:0;right:0;">
                    </div>
                
                    <div class="modal-body modal-mobile-body">
                         <component v-bind:is="dialogBody"  :window="this" :dialogBodyData="dialogBodyData"></component>
                    </div>
                </div>
            
        </div>
    </div>
</template>
<script>
import DialogBody from './DialogBody';

Vue.component('default-dialog-body-element', DialogBody);

export default {
    data : {
        dialogBodyData : {},
    },

    props: {
        title : {
            type: String,
            required: true
        },
        
        title : {
            type: String,
            required: true
        },
    },
    
    computed: {
         dialogBody : function() {
            return 'default-dialog-body-element';
         }
    },

}
</script>