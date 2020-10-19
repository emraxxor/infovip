<template>
    <div v-on:click="onClickItem" class="item item-waterfall" style="">
            <!-- Member Item Start -->
            <div class="member--item online">
                <div class="">
                        <img :src="image(wdata)" class="img-fluid img-thumbnail" alt="">
                </div>

                <div class="name">
                    <h6 v-if="wdata.title" class="h6 fs--12">
                        {{wdata.title}}
                    </h6>
                    <h6 v-else>
                        No title
                    </h6>
                </div>
            </div>
            <!-- Member Item End -->
    </div>
</template>
<script>
import DefaultElementComponentVue from '../../../ui/waterfall/DefaultElementComponent.vue';

export default {

    extends : DefaultElementComponentVue,
    
    props: {
        onClick : {
            type : Function,
            required : true
        }
    },

    created : function() {

    },

    computed : {
      
    },

    methods : {

        onClickItem : function() {
            this.onClick(this.wdata);
        },

        image : function(item) {
            if ( item.data !== undefined  ) 
                return '/public/media/image?' + item.data;
        
            return '/public/media/image?noimage';
        }
    },



    mounted: function () {
    },

}
</script>