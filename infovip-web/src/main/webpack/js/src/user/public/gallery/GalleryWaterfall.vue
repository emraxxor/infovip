<template>
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
         <gallery-box :wdata="item"></gallery-box>
        </waterfall-element>
    </waterfall>
</template>
<script>

import WaterfallVue from '../../../ui/waterfall/Waterfall.vue'
import WaterfallElementVue from 'vue-waterfall/lib/waterfall-slot'
import GalleryPhotoElementVue from './GalleryPhotoElement.vue'

Vue.component('gallery-photo-element', GalleryPhotoElementVue);

export default {

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
        'gallery-box' : GalleryPhotoElementVue
    },

    mounted: function () {
    },

    computed: {
    },

    methods : {
        onDataLoad(data) {
            const imgs = data.data.data;
            imgs.forEach( e => { e['height'] = e.height + 100 ; } )
            imgs.forEach( e => this.items.push(e)  );
        }
    }

  }
</script>