<template>
    <div class="media--details">
            <div class="media--author user clearfix">
                <div class="media--avatar float--left" data-overlay="0.3" data-overlay-color="primary">
                    <img v-bind:src="userImage" alt="">
                </div>

                <div class="media--author-info ov--h">
                    <p class="name fs--14 fw--700 text-darkest">  {{ user.userName }} </p>
                    <p class="date"><i class="ml--8 fa fa-globe"> {{ user.lastSeen }}</i></p>
                </div>
            </div>

            <!-- Media Meta Start -->
            <div class="media--meta">
                <ul class="nav">
                    <li>
                        <a href="#">
                            <i class="bg-primary text-white mr--6 fa fa-hand-o-right"></i>
                            <span>LIKE_NUMBER</span>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <i class="bg-primary text-white mr--6 fa fa-comment-o"></i>
                            <span>SHOW_COMMENTS_BOX</span>
                        </a>
                    </li>
                </ul>
            </div>
            <!-- Media Meta End -->

            <!-- Media Comments Start -->
            <div class="media--comments row p-5">
                    <textarea v-model="message"></textarea>
                    <button class="btn btn-primary m-auto" style="margin-top: 5% !important;" @click="onSubmitMessage">Send</button>
            </div>
            <!-- Media Comments End -->

            <!-- Media All Comments Start -->
            <div class="media--all-comments text-center" style="max-height: 300px; overflow:auto;">
                <div v-for="comment in comments" class="media--comment">
                                <!-- Media Author Start -->
                                <div class="media--author clearfix" style="text-align: left;">
                                    <div class="media--avatar float--left" data-overlay="0.3" data-overlay-color="primary">
                                        <img v-bind:src="uImage(comment)" alt="">
                                    </div>

                                    <div class="media--author-info ov--h">
                                        <p class="name"><a href="#" class="btn-link">{{comment.commenterName}}</a> Replied</p>
                                        <p class="date"><i class="mr--8 fa fa-clock-o"></i>{{ dateFormatter(comment.creationTime) }}</p>

                                        <!-- Media Comment Content Start -->
                                        <div class="media--comment-content">
                                            <p>{{comment.comment}}</p>
                                        </div>
                                        <!-- Media Comment Content End -->
                                    </div>
                                </div>
                                <!-- Media Author End -->
                </div>

                <a href="#" v-on:click="fetchComments" class="btn-link">Show more</a>
            </div>
            <!-- Media All Comments End -->
        </div>
        <!-- Media Details End -->
</template>

<script>
  


import Controller from '../../../ui/core/Controller';
 
export default {

        mixins: [Controller],

        data() {
            return {
                user  : {},
                message : '',
                comments : [],
                token : null,
            }
        },

        computed : {
            userImage : function () {
                return WEB_DIR + "/user/image?" + this.user.picture ;
            },

        },

        beforeCreate() {
        },

        
        created() {
            this.user = CurrentUser.info();
            this.fetchComments();
        },

        methods : {
            uImage : function(v) {
                    return "/api/public/image/user/" + v.commenter ; 
            },

            dateFormatter : function(v) {
                 return Vue.moment(v).format("YYYY-MM-DD HH:mm:ss" );
            },

            fetchComments : function() {
                const dialog = window.components.PHOTO_WINDOW;

                let request = {
                    photoId : dialog.getPhotoData().documentId
                };
                
                if ( this.token != null ) 
                    request.token = this.token;
                
                const result = function(result, o ) {
                    const { data } = result;
                    data.data.forEach(element => o.comments.push(element) );    
                    o.token = data.token;             
                }

                 this.post('/user/photo/comments', request, result, this );    
            },

            onSubmitMessage : function(event) {
                const dialog = window.components.PHOTO_WINDOW;

                if ( this.message !== '' ) {

                    const data = {
                        comment : this.message	,
                        mediaId : dialog.getPhotoData().mediaId,
                        photoId : dialog.getPhotoData().documentId
                    };
                            
                    const result = function(result, o ) {
                        result = result.data;
                        
                        
                        if ( result[0] != undefined && result[0]['type'] != undefined ) {
                                DefaultFormValidatorHandlerDialog(result).display();	
                        } else {
                            o.comments.unshift(result);
                            o.message = '';
                        }
                    }

                    this.post('/user/photo/comment', data, result, this );    
                }                                             
            }
    
        }

    }
</script>
