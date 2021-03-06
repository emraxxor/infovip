<template>

<!-- Activity Item Start -->
<div class="activity--item">
    <div class="activity--avatar">
        <a :href="`/user/${item.uid}/profile`">
            <img :src="`/public/image/user/${item.uid}`" alt="">
        </a>
    </div>

    <div class="activity--info fs--14">
        <div class="activity--header">
            <p><a :href="`/user/${item.uid}/profile`">{{item.userName}}</a></p>
        </div>

        <div class="activity--meta fs--12">
            <p><i class="fa mr--8 fa-clock-o"></i>{{formatter(item.date)}}</p>
        </div>

        <div class="activity--content" v-html="item.text"></div>
        
        <div class="details">
            Összesen kedvelték : <span class="like-count">{{item.totalLikeCount}}</span>
        </div>
        
        <div class="actions">
            <button v-if="!liked" type="button" @click="onLike($event)" class="btn btn-primary">Like</button>
            <button v-else type="button" @click="onUnlike($event)" class="btn">Unlike</button>
            <button type="button" @click="onComment($event)" class="btn btn-primary">Comment</button>
        </div>
        <div v-if="commentBox" ref="comment">
            <default-tiny-mce-editor ref="editor" width="80%" height="30" />
            <button type="button" @click="sendComment($event)" class="btn btn-primary">Send</button>
        </div>
        
        <div class="activity--comments fs--12">
            <ul v-if="item.comments && item.comments.length > 0" class="acomment--items">
                <!-- Comment Block -->
                    <li v-for="comment in item.comments" :key="comment.documentId">
                        <activity-item-comment :item="comment" />
                    </li>

                <!-- Comment Block End -->
            </ul>
        </div>
        
        
    </div>
    

</div>
<!-- Activity Item End -->
</template>
<script>
import DefaultTinyMceEditor from '../../../components/tinymce/DefaultTinyMceEditor.vue';
import Controller from '../../../ui/core/Controller';
import User from '../../../ui/core/User';
import ActivityItemComment from './ActivityItemComment.vue';
export default {
  components: { ActivityItemComment, DefaultTinyMceEditor },

       data() {
           return {
               commentBox: false
           }
       },

       props: {
           item : { 
               required: true ,
               default : {
                   uid: 0,
                   userName: '',
                   totalLikeCount: 0,
                   documentId: '',
                   routing: ''
               }
           }
       },

       computed: {
           liked() {
               return this.item.likes != null && this.item.likes.length > 0  && this.item.likes.find( e => e.uid === this.currentUser.userId ) != null 
           }
       },

       mixins: [Controller, User],

       mounted () {
       },

       methods: {
           formatter(v) {
                return Vue.moment(v).format("YYYY-MM-DD HH:mm");
           },

           onLike(event) {
               this.httpForm().post(`/activity/like`, {
                   id: this.item.documentId,
                   routing: this.item.routing
               }).then(f => {
                   this.$store.commit('incLike', {...this.item,cuid:this.currentUser.userId, req:f.data})
               })
           },

           onUnlike(event) {
               this.httpForm().post(`/activity/nolike`, {
                   id: this.item.documentId,
                   routing: this.item.routing
               }).then(f => {
                   this.$store.commit('decLike', {...this.item,cuid:this.currentUser.userId, req: f.data})
               })
           },

           onComment(event) {
                this.commentBox = !this.commentBox
           },

           sendComment(event) {
               this.httpClient().post(
                   `/activity/comment`, 
                   {
                       id: this.item.documentId,
                       text: this.$refs.editor.getText()
                   }
               ).then(e => {
                   this.$store.commit('addComment', e.data)
                   this.commentBox = !this.commentBox
               })
           }

       },
}
</script>