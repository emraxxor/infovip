<template>
<div class="acomment--item clearfix">
    <div class="acomment--avatar">
        <a :href="`/user/${item.uid}/profile`">
            <img :src="`/public/image/user/${item.uid}`" alt="">
        </a>
    </div>

    <div class="acomment--info">
        <div class="acomment--header">
            <p><a :href="`/user/${item.uid}/profile`">{{item.userName}}</a> Replied</p>
        </div>

        <div class="acomment--meta">
            <p><i class="fa mr--8 fa-clock-o"></i>{{format(item.date)}}</p>
        </div>

        <div class="acomment--content" v-html="item.text"></div>

        <div class="details">
            Összesen kedvelték : <span class="like-count">{{item.totalLikeCount}}</span>
        </div>

        <div class="actions">
            <button v-if="!liked" type="button" @click="onLike($event)" class="btn btn-primary">Like</button>
            <button v-else type="button" @click="onUnlike($event)" class="btn">Unlike</button>
            <button type="button" @click="onComment($event)" class="btn btn-primary">Reply</button>
        </div>
         <div v-if="commentBox" ref="comment">
            <default-tiny-mce-editor ref="editor" width="80%" height="30" />
            <button type="button" @click="sendComment($event)" class="btn btn-primary">Send</button>
        </div>

        <div class="activity--comments fs--12">
            <ul v-if="item.replies && item.replies.length > 0" class="acomment--items">
                <!-- Comment Block -->
                    <li v-for="reply in item.replies" :key="reply.documentId">
                        <activity-item-comment-reply :item="reply" />
                    </li>
                <!-- Comment Block End -->
            </ul>
        </div>


    </div>
</div>
</template>
<script>
import { DateFormatter } from '@/ui/core/DateFormatter'
import DefaultTinyMceEditor from '../../../components/tinymce/DefaultTinyMceEditor.vue'
import Controller from '../../../ui/core/Controller'
import ActivityItemCommentReply from './ActivityItemCommentReply.vue'
import User from '../../../ui/core/User'

export default {
    components: { DefaultTinyMceEditor, ActivityItemCommentReply },

    mixins: [Controller, User],

    props: {

        item: {
            required: true,
            default: {} 
        },
    },

    computed: {
        liked() {
            return this.item.likes.find( e => e.uid === this.currentUser.userId ) != null 
        }
    },

    data() {
        return {
            commentBox: false
        }
    },

    methods: {
        format(date) {
            return DateFormatter.format(date)
        },


        onLike(event) {
               this.httpForm().post(`/activity/like`, {
                   id: this.item.documentId,
                   routing: this.item.routing,
               }).then(f => {
                   this.$store.commit('incCommentLike', {...this.item, cuid: this.currentUser.userId, resp:f.data})
               })
           },

           onUnlike(event) {
               this.httpForm().post(`/activity/nolike`, {
                   id: this.item.documentId,
                   routing: this.item.routing,
               }).then(f => {
                   this.$store.commit('decCommentLike', {...this.item, cuid: this.currentUser.userId, resp: f.data})
               })
           },

           onComment(event) {
                this.commentBox = !this.commentBox
           },

           sendComment(event) {
               this.httpForm().post(
                   `/activity/reply`, 
                   {
                       id: this.item.documentId,
                       routing: this.item.routing,
                       text: this.$refs.editor.getText()
                   }
               ).then(e => {
                   this.$store.commit('addReply', { ...this.item, resp: e.data } )
                   this.commentBox = !this.commentBox
               })
           }
    },
}

</script>