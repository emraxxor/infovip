<template>
<div class="row">

    <div class="col-md-8">
        <figure class="media--img" style="max-height:500px; overflow:auto;">
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

                    <PhotoInformation :comments="comments" :photoId="photoId"></PhotoInformation>
                        

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
import Controller from '@/ui/core/Controller'
import store from '@/user/store/gallery/GalleryStore'
import PhotoInformation from '@/user/ui/photo/PhotoInformation'


export default {
    store : store,

    mixins: [Controller],

    components : {
        PhotoInformation
    },

    data : () => ({
            user : () => ({}),
            comments : [],
            photo : {},
            photoId : undefined,
            mediaImage : '/public/media/image?noimage',
            token : null,   
    }),

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

    watch : {
        item : function() {
            
        }
    },

    mounted()  {
        this.comments = []
        this
            .fetch()
            .then( o => {
                o.v.user = o.res;
                o.v.fetchComments();
            });
        this.mediaImage = '/public/media/image?' + this.item.data + '_BIG';
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

            this.photoId = this.item.documentId
            
            if ( this.token != null ) 
                request.token = this.token;
            

            this.httpForm().post('/public/media/comments', request)
                .then(result => {
                     const { data } = result;
                     data.data.forEach(element => this.comments.push(element) );    
                     this.token = data.token;             
                });    
        },

        
    }


}
</script>