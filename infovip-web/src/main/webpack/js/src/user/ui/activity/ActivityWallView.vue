<template>

<div>
    <div style="padding-top: 0px; padding-bottom: 1px; position: static; transform: none;">
    <div class="main--content-inner drop--shadow">
                
                <!-- Filter Nav Start -->
                <div class="filter--nav pb--60 clearfix">
                    <div class="filter--link float--left">
                        <h2 class="h4">Say Something</h2>
                            <default-tiny-mce-editor ref="editor" />
                    </div>
                </div>
                <button type="button" id="send-post" @click="onMessage($event)" class="btn btn-primary">Send</button>
                <!-- Filter Nav End -->
    </div>
    </div>		

    <div
        style="padding-top: 0px; padding-bottom: 1px; position: static; transform: none; margin-top: 20px;">
        <div class="main--content-inner drop--shadow">
            <!-- Filter Nav Start -->
            <div class="filter--nav pb--60 clearfix">
                <div class="filter--link float--left">
                    <h2 class="h4">
                        <a href="/activity" class="btn-link">Activities</a>
                    </h2>
                </div>

            </div>
            <!-- Filter Nav End -->

            <!-- Activity List Start -->
            <div class="activity--list">
                <!-- Activity Items Start -->
                <ul v-if="activities.data.length > 0" class="activity--items">
                    <li v-for="item in activities.data" :key="item.id">
                            <activity-item :item="item" />
                    </li>
                </ul>
                <!-- Activity Items End -->
            </div>
            <!-- Activity List End -->
        </div>
        <div class="load-more--btn pt--30 text-center">
            <button class="btn btn-animate" @click="onLoadMore($event)"> 
                <span>See MoreActivities<i class="fa ml--10 fa-caret-right"></i></span>
            </button>
        </div>

        <div class="resize-sensor"
            style="position: absolute; left: 0px; top: 0px; right: 0px; bottom: 0px; overflow: hidden; z-index: -1; visibility: hidden;">
            <div class="resize-sensor-expand"
                style="position: absolute; left: 0; top: 0; right: 0; bottom: 0; overflow: hidden; z-index: -1; visibility: hidden;">
                <div
                    style="position: absolute; left: 0px; top: 0px; transition: all 0s ease 0s; width: 790px; height: 2751px;"></div>
            </div>
            <div class="resize-sensor-shrink"
                style="position: absolute; left: 0; top: 0; right: 0; bottom: 0; overflow: hidden; z-index: -1; visibility: hidden;">
                <div
                    style="position: absolute; left: 0; top: 0; transition: 0s; width: 200%; height: 200%">
                </div>
            </div>
        </div>
    </div>
</div>
</template>
<script>
import { mapActions } from 'vuex'
import DefaultTinyMceEditor from '../../../components/tinymce/DefaultTinyMceEditor.vue'
import User from '../../../ui/core/User'
import ActivityItem from './ActivityItem.vue'
import ActivityStore from './store/ActivityStore'

/**
 * Activity wall
 * 
 * @author Attila Barna
 */
export default {

    components: { ActivityItem, DefaultTinyMceEditor },
    
    store: ActivityStore,

    mixins: [User],

    computed: {
          activities() {
              return this.$store.state.activities;
          }
    },

    data : () => ({

    }),

    mounted()  {
        this.fetchInit();
    },


    methods : {

            ...mapActions([
                'fetchInit'
            ]),

            onMessage(event) {
                if ( this.$refs.editor.getText() !== '' ) {
                    this.httpClient().post(
                        `/activity/add`,
                        {
                            text: this.$refs.editor.getText()
                        }
                    ).then(f => {
                        this.$store.commit('addElement', { resp : f, user: this.currentUser })
                    }).catch(e => {
                        console.error(e)
                    })
                }
            },

            onLoadMore(event) {
                this.$store.dispatch('fetch', this.activities.token);
            }

    }

}

</script>