<template>
    <div :id="waterfallId" :class="waterfallContainer" class="">
       <div class="vue-waterfall" :style="style">
            <slot></slot>
       </div>
    </div>
</template>

<script>
  import Controller from '../core/Controller';
  import VueWaterfall from 'vue-waterfall/lib/waterfall'


  /**
  * @author Attila Barna
  */
  export default{

    mixins: [Controller],

    extends : VueWaterfall,

    components : {

    },


    data() {
        return {
            style: {
                height: '',
                overflow: '',
                width: '100%',
            },
            dataToken : null,
            lastSize : undefined 
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
        waterfallContainer : {
            type: String,
            required : false,
            default : ''
        }
    },
    

    created() {
        this.init();
    },

    computed: {

    },

    mounted: function () {
        this.scroll();
    },

    watch: {

    },

    methods : {

         init() {
             this.httpClient().get(this.sourceUrl, {}).then( e => this.load(e) );
         },

         scroll () {
            window.onscroll = () => {
                
                if ( this.lastSize !== undefined && this.lastSize == 0)
                    return;

                const bottomOfWindow = window.scrollY > this.$el.getClientRects()[0].height ;
                
                if (bottomOfWindow) {
                    this.httpClient()
                        .get(this.sourceUrl, { token : this.dataToken })
                        .then( e => this.load(e) );
                }
                
                this.httpForm().post(this.sourceUrl, { token : this.dataToken }, e => this.load(e)  );
                
            };
         },


        load(e) {
            this.dataToken = e.data.token;
            this.lastSize = e.data.count;
            this.$emit('onDataLoaded', e);
        },

    }

  }
</script>