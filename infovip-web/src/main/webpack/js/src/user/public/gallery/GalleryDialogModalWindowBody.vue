<template>
<div class="row">

    <div class="col-md-8">
        <figure class="media--img">
            <img class="photo img-responsive responsive" :src="mediaImage" alt="">
            <p class="figcaption fs--14 fw--700 text-white"></p>
        </figure>
    </div>

    <div class="col-md-4">

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

                    </div>
                    <!-- Media All Comments End -->
                </div>
                <!-- Media Details End -->
    </div>  
</div>
</template>
<script>

import Controller from '../../../ui/core/Controller';

export default {
    mixins: [Controller],

    data : () => {
        return {
            user : () => ({}),
            comments : [],
            mediaImage : '/public/media/image?noimage',
            token : null,
        }
    },

    props: {
      item : {
          required : true
      },
    },

    computed : {
        userImage : function() {
            if ( this.user.userId != null )
                return "/public/image/user/" + this.user.userId;
            return '/public/media/image?noimage';
        }
    },

    created : function() {
        
    },

    mounted : function() {

    },

    watch : {
        item : function() {
            this
                .fetch()
                .then( o => {
                    o.v.user = o.res;
                    o.v.fetchComments();
                });
            this.mediaImage = '/public/media/image?' + this.item.data;
        }
    },

    methods : {
        uImage : function(v) {
            return "/public/image/user/" + v.commenter ; 
        },
        
        dateFormatter : function(v) {
            return Vue.moment(v).format("YYYY-MM-DD HH:mm:ss" );
        },
        
        fetch : function() {
            const that = this;
            return new Promise(
                (resolve, reject) => 
                    that.get('/public/user/info/' + that.item.userId , {} , (d,o) =>  resolve( { res : d.data.data , v : o } ) , that ) )
        },

        fetchComments : function() {

            let request = {
                photoId : this.item.documentId
            };
            
            if ( this.token != null ) 
                request.token = this.token;
            
            const result = function(result, o ) {
                const { data } = result;
                data.data.forEach(element => o.comments.push(element) );    
                o.token = data.token;             
            }

            this.post('/public/media/comments', request, result, this );    
        },
    }


}
</script>