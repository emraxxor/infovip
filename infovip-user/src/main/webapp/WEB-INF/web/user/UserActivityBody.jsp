<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<c:if test="${not empty isAuthenticated and isAuthenticated == true }">


<tiles:insertTemplate template="${Configuration.WEB_DIRECTORY}/user/header/header.jsp"></tiles:insertTemplate>  			


<section class="page--wrapper pt--80 pb--20" style="transform: none;">
            <div class="container" style="transform: none;">
                <div class="row" style="transform: none;">
                    <!-- Main Content Start -->
                    <div class="main--content col-md-8 pb--60" data-trigger="stickyScroll" style="position: relative; overflow: visible; box-sizing: border-box; min-height: 1px;">
                        

                        <!-- Load More Button Start -->
                        
                        <!-- Load More Button End -->
                    <div class="theiaStickySidebar" style="padding-top: 0px; padding-bottom: 1px; position: static; transform: none;"><div class="main--content-inner drop--shadow">
                            <!-- Content Nav Start -->
                            <div class="content--nav pb--30">
                                <ul class="nav ff--primary fs--14 fw--500 bg-lighter">
                                    <li class="active"><a href="member-activity-personal.html">Activity</a></li>
                                    <li><a href="member-profile.html">Profile</a></li>
                                    <li><a href="member-friends.html">Friends</a></li>
                                    <li><a href="member-groups.html">Groups</a></li>
                                    <li><a href="member-forum-topics.html">Forum</a></li>
                                    <li><a href="member-media-all.html">Media</a></li>
                                </ul>
                            </div>
                            <!-- Content Nav End -->

                            <!-- Filter Nav Start -->
                            <div class="filter--nav pb--60 clearfix">
                                <div class="filter--links float--left">
                                    <ul class="nav ff--primary">
                                        <li class="active"><a href="member-activity-persoanl.html">Personal</a></li>
                                        <li><a href="member-activity-mentions.html">Mentions</a></li>
                                        <li><a href="member-activity-favorites.html">Favorites</a></li>
                                        <li><a href="member-activity-friends.html">Friends</a></li>
                                        <li><a href="member-activity-groups.html">Groups</a></li>
                                    </ul>
                                </div>

                                <div class="filter--options float--right">
                                    <label>
                                        <span class="fs--14 ff--primary fw--500 text-darker">Show By :</span>

                                        <select name="activityfilter" class="form-control form-sm" data-trigger="selectmenu" style="display: none;">
                                            <option value="updates" selected="">Updates</option>
                                            <option value="friendships">Friendships</option>
                                            <option value="group-updates">Group Updats</option>
                                            <option value="membership">Membership</option>
                                            <option value="topics">Topics</option>
                                            <option value="replies">Replies</option>
                                            <option value="posts">Posts</option>
                                            <option value="comments">Comments</option>
                                        </select><div class="custom-select-menu" tabindex="0"><span class="selection-made">Updates</span><ul data-select-name="activityfilter" style="display: none;"><li data-option-value="updates" class="selected">Updates</li><li data-option-value="friendships">Friendships</li><li data-option-value="group-updates">Group Updats</li><li data-option-value="membership">Membership</li><li data-option-value="topics">Topics</li><li data-option-value="replies">Replies</li><li data-option-value="posts">Posts</li><li data-option-value="comments">Comments</li></ul><input type="hidden" name="activityfilter" value="updates"></div>
                                    </label>
                                </div>
                            </div>
                            <!-- Filter Nav End -->

                            <!-- Activity List Start -->
                            <div class="activity--list">
                                <!-- Activity Items Start -->
                                <ul class="activity--items nav">
                                    <li>
                                        <!-- Activity Item Start -->
                                        <div class="activity--item">
                                            <div class="activity--avatar">
                                                <a href="member-activity-personal.html">
                                                    <img src="img/activity-img/avatar-01.jpg" alt="">
                                                </a>
                                            </div>

                                            <div class="activity--info fs--14">
                                                <div class="activity--header">
                                                    <p><a href="member-activity-personal.html">Eileen K. Ruiz</a> posted an update</p>
                                                </div>

                                                <div class="activity--meta fs--12">
                                                    <p><i class="fa mr--8 fa-clock-o"></i>Yeasterday at 08:20 am</p>
                                                </div>

                                                <div class="activity--content">
                                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quibusdam quo deserunt a suscipit perferendis dicta at eveniet officiis!</p>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- Activity Item End -->
                                    </li>
                                    <li>
                                        <!-- Activity Item Start -->
                                        <div class="activity--item">
                                            <div class="activity--avatar">
                                                <a href="member-activity-personal.html">
                                                    <img src="img/activity-img/avatar-01.jpg" alt="">
                                                </a>
                                            </div>

                                            <div class="activity--info fs--14">
                                                <div class="activity--header">
                                                    <p><a href="member-activity-personal.html">Eileen K. Ruiz</a> posted an update in the group <a href="group-home.html">Crazy Music Lovers</a></p>
                                                </div>

                                                <div class="activity--meta fs--12">
                                                    <p><i class="fa mr--8 fa-clock-o"></i>Yeasterday at 08:20 am</p>
                                                </div>

                                                <div class="activity--content">
                                                    <p>There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing</p>
                                                </div>

                                                <div class="activity--action fw--700">
                                                    <a href="#">See More...</a>
                                                </div>

                                                <div class="activity--comments fs--12">
                                                    <ul class="acomment--items nav">
                                                        <li>
                                                            <div class="acomment--item clearfix">
                                                                <div class="acomment--avatar">
                                                                    <a href="member-activity-personal.html">
                                                                        <img src="img/activity-img/avatar-04.jpg" alt="">
                                                                    </a>
                                                                </div>

                                                                <div class="acomment--info">
                                                                    <div class="acomment--header">
                                                                        <p><a href="#">Leticia J. Espinosa</a> Replied</p>
                                                                    </div>

                                                                    <div class="acomment--meta">
                                                                        <p><i class="fa mr--8 fa-clock-o"></i>Yeasterday at 08:20 am</p>
                                                                    </div>

                                                                    <div class="acomment--content">
                                                                        <p>Well Said!</p>
                                                                        <p>Love it..... <span style="color: #ec407a;">♥ ♥ ♥</span></p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- Activity Item End -->
                                    </li>
                                    <li>
                                        <!-- Activity Item Start -->
                                        <div class="activity--item">
                                            <div class="activity--avatar">
                                                <a href="member-activity-personal.html">
                                                    <img src="img/activity-img/avatar-01.jpg" alt="">
                                                </a>
                                            </div>

                                            <div class="activity--info fs--14">
                                                <div class="activity--header">
                                                    <p><a href="member-activity-personal.html">Eileen K. Ruiz</a> and <a href="member-activity-personal.html">Leticia J. Espinosa</a> are now friends</p>
                                                </div>

                                                <div class="activity--meta fs--12">
                                                    <p><i class="fa mr--8 fa-clock-o"></i>Yeasterday at 08:20 am</p>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- Activity Item End -->
                                    </li>
                                    <li>
                                        <!-- Activity Item Start -->
                                        <div class="activity--item">
                                            <div class="activity--avatar">
                                                <a href="member-activity-personal.html">
                                                    <img src="img/activity-img/avatar-01.jpg" alt="">
                                                </a>
                                            </div>

                                            <div class="activity--info fs--14">
                                                <div class="activity--header">
                                                    <p><a href="member-activity-personal.html">Eileen K. Ruiz</a> posted an update in the group <a href="group-home.html">Crazy Music Lovers</a></p>
                                                </div>

                                                <div class="activity--meta fs--12">
                                                    <p><i class="fa mr--8 fa-clock-o"></i>Yeasterday at 08:20 am</p>
                                                </div>

                                                <div class="activity--content">
                                                    <div class="link--embed">
                                                        <a class="link--url" href="https://www.youtube.com/watch?v=YE7VzlLtp-4" data-trigger="video_popup"></a>

                                                        <div class="link--video">
                                                            <img src="img/activity-img/link-video-poster.jpg" alt="">
                                                        </div>

                                                        <div class="link--info fs--12">
                                                            <div class="link--title">
                                                                <h4 class="h6">There are many variations of passages of Lorem Ipsum available, but the majority have suffered</h4>
                                                            </div>

                                                            <div class="link--desc">
                                                                <p>There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing</p>
                                                            </div>

                                                            <div class="link--rel ff--primary text-uppercase">
                                                                <p>www.unknownneonnettle.com</p>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- Activity Item End -->
                                    </li>
                                    <li>
                                        <!-- Activity Item Start -->
                                        <div class="activity--item">
                                            <div class="activity--avatar">
                                                <a href="member-activity-personal.html">
                                                    <img src="img/activity-img/avatar-01.jpg" alt="">
                                                </a>
                                            </div>

                                            <div class="activity--info fs--14">
                                                <div class="activity--header">
                                                    <p><a href="member-activity-personal.html">Eileen K. Ruiz</a> posted an update in the group <a href="group-home.html">Crazy Music Lovers</a></p>
                                                </div>

                                                <div class="activity--meta fs--12">
                                                    <p><i class="fa mr--8 fa-clock-o"></i>Yeasterday at 08:20 am</p>
                                                </div>

                                                <div class="activity--content">
                                                    <div class="gallery--embed" data-trigger="gallery_popup">
                                                        <ul class="nav AdjustRow" style="position: relative; height: 521.218px;">
                                                            <li style="position: absolute; left: 0px; top: 0px;">
                                                                <a href="img/activity-img/gallery-embed-01.jpg">
                                                                    <img src="img/activity-img/gallery-embed-01.jpg" alt="">
                                                                </a>
                                                            </li>
                                                            <li style="position: absolute; left: 327px; top: 0px;">
                                                                <a href="img/activity-img/gallery-embed-02.jpg">
                                                                    <img src="img/activity-img/gallery-embed-02.jpg" alt="">
                                                                </a>
                                                            </li>
                                                            <li style="position: absolute; left: 0px; top: 260px;">
                                                                <a href="img/activity-img/gallery-embed-03.jpg">
                                                                    <img src="img/activity-img/gallery-embed-03.jpg" alt="">
                                                                </a>
                                                            </li>
                                                            <li style="position: absolute; left: 327px; top: 260px;">
                                                                <a href="img/activity-img/gallery-embed-04.jpg" data-overlay="0.5">
                                                                    <img src="img/activity-img/gallery-embed-04.jpg" alt="">
                                                                    <span>24+ More</span>
                                                                </a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- Activity Item End -->
                                    </li>
                                    <li>
                                        <!-- Activity Item Start -->
                                        <div class="activity--item">
                                            <div class="activity--avatar">
                                                <a href="member-activity-personal.html">
                                                    <img src="img/activity-img/avatar-01.jpg" alt="">
                                                </a>
                                            </div>

                                            <div class="activity--info fs--14">
                                                <div class="activity--header">
                                                    <p><a href="member-activity-personal.html">Eileen K. Ruiz</a> joined the group <a href="group-home.html">Crazy Music Lovers</a></p>
                                                </div>

                                                <div class="activity--meta fs--12">
                                                    <p><i class="fa mr--8 fa-clock-o"></i>Yeasterday at 08:20 am</p>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- Activity Item End -->
                                    </li>
                                </ul>
                                <!-- Activity Items End -->
                            </div>
                            <!-- Activity List End -->
                        </div><div class="load-more--btn pt--30 text-center">
                            <a href="#" class="btn btn-animate">
                                <span>See More Activities<i class="fa ml--10 fa-caret-right"></i></span>
                            </a>
                        </div><div class="resize-sensor" style="position: absolute; left: 0px; top: 0px; right: 0px; bottom: 0px; overflow: hidden; z-index: -1; visibility: hidden;"><div class="resize-sensor-expand" style="position: absolute; left: 0; top: 0; right: 0; bottom: 0; overflow: hidden; z-index: -1; visibility: hidden;"><div style="position: absolute; left: 0px; top: 0px; transition: all 0s ease 0s; width: 790px; height: 2211px;"></div></div><div class="resize-sensor-shrink" style="position: absolute; left: 0; top: 0; right: 0; bottom: 0; overflow: hidden; z-index: -1; visibility: hidden;"><div style="position: absolute; left: 0; top: 0; transition: 0s; width: 200%; height: 200%"></div></div></div></div></div>
                    <!-- Main Content End -->

                    <!-- Main Sidebar Start -->
                    <div class="main--sidebar col-md-4 pb--60" data-trigger="stickyScroll" style="position: relative; overflow: visible; box-sizing: border-box; min-height: 1px;">
                        <!-- Widget Start -->
                        
                        <!-- Widget End -->

                        <!-- Widget Start -->
                        
                        <!-- Widget End -->

                        <!-- Widget Start -->
                        
                        <!-- Widget End -->

                        <!-- Widget Start -->
                        
                        <!-- Widget End -->

                        <!-- Widget Start -->
                        
                        <!-- Widget End -->
                    <div class="theiaStickySidebar" style="padding-top: 0px; padding-bottom: 1px; position: static; transform: none; top: 0px; left: 1162.5px;"><div class="widget">
                            <h2 class="h4 fw--700 widget--title">Find A Buddy</h2>

                            <!-- Buddy Finder Widget Start -->
                            <div class="buddy-finder--widget">
                                <form action="#">
                                    <div class="row">
                                        <div class="col-xs-6 col-xxs-12">
                                            <div class="form-group">
                                                <label>
                                                    <span class="text-darker ff--primary fw--500">I Am</span>

                                                    <select name="gender" class="form-control form-sm" data-trigger="selectmenu" style="display: none;">
                                                        <option value="male">Male</option>
                                                        <option value="female">Female</option>
                                                        <option value="other">Other</option>
                                                    </select><div class="custom-select-menu" tabindex="0"><span class="selection-made">Male</span><ul data-select-name="gender" style="display: none;"><li data-option-value="male" class="selected">Male</li><li data-option-value="female">Female</li><li data-option-value="other">Other</li></ul><input type="hidden" name="gender" value="male"></div>
                                                </label>
                                            </div>
                                        </div>

                                        <div class="col-xs-6 col-xxs-12">
                                            <div class="form-group">
                                                <label>
                                                    <span class="text-darker ff--primary fw--500">Looking For</span>

                                                    <select name="lookingfor" class="form-control form-sm" data-trigger="selectmenu" style="display: none;">
                                                        <option value="female">Female</option>
                                                        <option value="male">Male</option>
                                                        <option value="other">Other</option>
                                                    </select><div class="custom-select-menu" tabindex="0"><span class="selection-made">Female</span><ul data-select-name="lookingfor" style="display: none;"><li data-option-value="female" class="selected">Female</li><li data-option-value="male">Male</li><li data-option-value="other">Other</li></ul><input type="hidden" name="lookingfor" value="female"></div>
                                                </label>
                                            </div>
                                        </div>

                                        <div class="col-xs-6 col-xxs-12">
                                            <div class="form-group">
                                                <label>
                                                    <span class="text-darker ff--primary fw--500">Age</span>

                                                    <select name="age" class="form-control form-sm" data-trigger="selectmenu" style="display: none;">
                                                        <option value="18to25">18 to 25</option>
                                                        <option value="25to30">25 to 30</option>
                                                        <option value="30to35">30 to 35</option>
                                                        <option value="35to40">35 to 40</option>
                                                        <option value="40plus">40+</option>
                                                    </select><div class="custom-select-menu" tabindex="0"><span class="selection-made">18 to 25</span><ul data-select-name="age" style="display: none;"><li data-option-value="18to25" class="selected">18 to 25</li><li data-option-value="25to30">25 to 30</li><li data-option-value="30to35">30 to 35</li><li data-option-value="35to40">35 to 40</li><li data-option-value="40plus">40+</li></ul><input type="hidden" name="age" value="18to25"></div>
                                                </label>
                                            </div>
                                        </div>

                                        <div class="col-xs-6 col-xxs-12">
                                            <div class="form-group">
                                                <label>
                                                    <span class="text-darker ff--primary fw--500">City</span>

                                                    <select name="city" class="form-control form-sm" data-trigger="selectmenu" style="display: none;">
                                                        <option value="newyork">New York</option>
                                                        <option value="California">California</option>
                                                        <option value="Atlanta">Atlanta</option>
                                                    </select><div class="custom-select-menu" tabindex="0"><span class="selection-made">New York</span><ul data-select-name="city" style="display: none;"><li data-option-value="newyork" class="selected">New York</li><li data-option-value="California">California</li><li data-option-value="Atlanta">Atlanta</li></ul><input type="hidden" name="city" value="newyork"></div>
                                                </label>
                                            </div>
                                        </div>

                                        <div class="col-xs-12">
                                            <div class="form-group">
                                                <label>
                                                    <span class="text-darker ff--primary fw--500">Filter Country</span>

                                                    <select name="city" class="form-control form-sm" data-trigger="selectmenu" style="display: none;">
                                                        <option value="unitedstates">United States</option>
                                                        <option value="australia">Australia</option>
                                                        <option value="turkey">Turkey</option>
                                                        <option value="vietnam">Vietnam</option>
                                                    </select><div class="custom-select-menu" tabindex="0"><span class="selection-made">United States</span><ul data-select-name="city" style="display: none;"><li data-option-value="unitedstates" class="selected">United States</li><li data-option-value="australia">Australia</li><li data-option-value="turkey">Turkey</li><li data-option-value="vietnam">Vietnam</li></ul><input type="hidden" name="city" value="unitedstates"></div>
                                                </label>
                                            </div>
                                        </div>

                                        <div class="col-xs-12">
                                            <button type="submit" class="btn btn-primary">Search</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <!-- Buddy Finder Widget End -->
                        </div><div class="widget">
                            <h2 class="h4 fw--700 widget--title">Notice</h2>

                            <!-- Text Widget Start -->
                            <div class="text--widget">
                                <p>There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some  look even slightly believable.</p>
                            </div>
                            <!-- Text Widget End -->
                        </div><div class="widget">
                            <h2 class="h4 fw--700 widget--title">Forums</h2>

                            <!-- Links Widget Start -->
                            <div class="links--widget">
                                <ul class="nav">
                                    <li><a href="sub-forums.html">User Interface Design<span>(12)</span></a></li>
                                    <li><a href="sub-forums.html">Front-End Engineering<span>(07)</span></a></li>
                                    <li><a href="sub-forums.html">Web Development<span>(37)</span></a></li>
                                    <li><a href="sub-forums.html">Social Media Marketing<span>(13)</span></a></li>
                                    <li><a href="sub-forums.html">Content Marketing<span>(28)</span></a></li>
                                </ul>
                            </div>
                            <!-- Links Widget End -->
                        </div><div class="widget">
                            <h2 class="h4 fw--700 widget--title">Archives</h2>

                            <!-- Nav Widget Start -->
                            <div class="nav--widget">
                                <ul class="nav">
                                    <li>
                                        <a href="#">
                                            <i class="fa fa-calendar-o"></i>
                                            <span class="text">Jan - July 2017</span>
                                            <span class="count">(86)</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <i class="fa fa-calendar-o"></i>
                                            <span class="text">Jan - Dce 2016</span>
                                            <span class="count">(328)</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <i class="fa fa-calendar-o"></i>
                                            <span class="text">Jan - Dec 2015</span>
                                            <span class="count">(427)</span>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                            <!-- Nav Widget End -->
                        </div><div class="widget">
                            <h2 class="h4 fw--700 widget--title">Advertisements</h2>

                            <!-- Ad Widget Start -->
                            <div class="ad--widget">
                                <a href="#">
                                    <img src="img/widgets-img/ad.jpg" alt="" class="center-block">
                                </a>
                            </div>
                            <!-- Ad Widget End -->
                        </div><div class="resize-sensor" style="position: absolute; left: 0px; top: 0px; right: 0px; bottom: 0px; overflow: hidden; z-index: -1; visibility: hidden;"><div class="resize-sensor-expand" style="position: absolute; left: 0; top: 0; right: 0; bottom: 0; overflow: hidden; z-index: -1; visibility: hidden;"><div style="position: absolute; left: 0px; top: 0px; transition: all 0s ease 0s; width: 400px; height: 1360px;"></div></div><div class="resize-sensor-shrink" style="position: absolute; left: 0; top: 0; right: 0; bottom: 0; overflow: hidden; z-index: -1; visibility: hidden;"><div style="position: absolute; left: 0; top: 0; transition: 0s; width: 200%; height: 200%"></div></div></div></div></div>
                    <!-- Main Sidebar End -->
                </div>
            </div>
</section>
	
</c:if>