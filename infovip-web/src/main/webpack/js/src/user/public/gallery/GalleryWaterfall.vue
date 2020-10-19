<template>
    <div>
        <waterfall :lineGap="200" :watch="items"
                :waterfallId="waterfallId"
                :sourceUrl="sourceUrl"
                @onDataLoaded="onDataLoad"
                align="center"
                    >
            <waterfall-element
                v-for="(item, index) in items"
                :width="item.width"
                :height="item.height"
                :order="index"
                :key="item.id"
            >
            <gallery-box :wdata="item" :onClick="onClickItem"></gallery-box>
            </waterfall-element>
        </waterfall>

        <dialog-window :title="dialogTitle" :open="displayDialog" :onClose="onCloseDialog">
            <dialog-body v-if="selectedItem" slot="dialogBody" :item="selectedItem"></dialog-body>
        </dialog-window>
    </div>
</template>
<script>

import WaterfallVue from '../../../ui/waterfall/Waterfall.vue'
import WaterfallElementVue from 'vue-waterfall/lib/waterfall-slot'
import GalleryPhotoElementVue from './GalleryPhotoElement.vue'
import DialogWindowVue from '../../../ui/dialog/DialogWindow.vue'
import GalleryDialogModalWindowBodyVue from './GalleryDialogModalWindowBody.vue';

export default {

    data() {
         return {
            displayDialog : false,
            selectedItem : {},
         }
    },

    props: {
        waterfallId : {
            type: String,
            required: true
        },
        sourceUrl : {
            type : String,
            required: true
        },
        items : {
             default: () => ([])
        },
    },

    components : {
        'waterfall' : WaterfallVue,
        'waterfall-element' : WaterfallElementVue,
        'gallery-box' : GalleryPhotoElementVue,
        'dialog-window' : DialogWindowVue,
        'dialog-body' : GalleryDialogModalWindowBodyVue
    },

    mounted: function () {
    },

    computed: {
        dialogTitle : function() {
            if (  this.selectedItem.title !== undefined ) 
                if ( this.selectedItem.title !== '' )
                    return this.selectedItem.title;

            return 'No title';
        }
    },

    methods : {
        onDataLoad(data) {
            const imgs = data.data.data;
            imgs.forEach( e => { e['height'] = e.height + 100 ; } )
            imgs.forEach( e => this.items.push(e)  );
        },

        onClickItem(item) {
            this.selectedItem = item;
            this.displayDialog = true;
        },

        onCloseDialog(e) {
            this.displayDialog = false;
        }
    }
  }
</script>