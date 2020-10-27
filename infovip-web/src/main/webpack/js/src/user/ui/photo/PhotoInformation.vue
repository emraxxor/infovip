<template>
     <div class="media--meta">
                        <ul class="nav">
                            <li>
                                <i @click="addLike" class="bg-primary text-white fa fa-thumbs-up"></i>
                                <span>{{ likes.length }}</span>
                            </li>
                            <li>
                                <i class="bg-primary text-white mr--6 fa fa-comment-o"></i>
                                <span>{{ comments.length }}</span>
                            </li>
                        </ul>
    </div>
</template>

<script>

import store from '@/user/store/gallery/GalleryStore'
import { mapActions, mapGetters } from 'vuex'


export default {

    store : store,

    data : () => ({
        user : undefined,
    }),


    props: {
        comments : {
            required: true
        },
        photoId : {
            required : true
        }
    },

    computed : {
        ...mapGetters( {  likes  : 'getLikes' } ),
        
    },

    watch : {
        photoId() {
            if ( this.photoId )
                this.updateLikes({
                    data : {
                        documentId : this.photoId
                    }
                })
        }
    },

    mounted() {
        if ( typeof(CurrentUser) !== "undefined" )
          this.user = CurrentUser.info()

         this.updateLikes({
                    data : {
                        documentId : this.photoId
                    }
        })
    },

    methods : {
        ...mapActions({ like : 'addLike', updateLikes : 'likes' }),

         addLike : async function() {
             if ( this.user !== undefined ) {
                try {
                    const resp = await this.like( {  
                        user : this.user.userId,
                        data : {
                            documentId : this.photoId
                        }
                    });
                } catch(error) {
                    console.error(error);
                }
             }
            
        }
    }
    
}

</script>