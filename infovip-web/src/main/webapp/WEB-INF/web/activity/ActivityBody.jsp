<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/tlds/module-manager" prefix="module"%>
<%@ taglib uri="/tlds/translate" prefix="tr"%>

<c:if test="${not empty isAuthenticated and isAuthenticated == true }">

<section class="page--wrapper pt--80 pb--20" style="transform: none;">
            <div class="container" style="transform: none;">
                <div class="row" style="transform: none;">
                    <!-- Main Content Start -->
                    <div class="main--content col-md-8 pb--60" data-trigger="stickyScroll" style="position: relative; overflow: visible; box-sizing: border-box; min-height: 1px;">
                        
				
				<div style="padding-top: 0px; padding-bottom: 1px; position: static; transform: none;">
				<div class="main--content-inner drop--shadow">
						    
						    <!-- Filter Nav Start -->
                            <div class="filter--nav pb--60 clearfix">
                                <div class="filter--link float--left">
                                    <h2 class="h4"><tr:tr val="Say something"></tr:tr></h2>
                                    <div data-uid='post-input-area'>
                                    </div>
                                </div>
                            </div>
		                    <button type="button" id="send-post" class="btn btn-primary"><tr:tr val="Send"></tr:tr></button>
                            <!-- Filter Nav End -->
				</div>
				</div>		


                        <!-- Load More Button Start -->
                        
                        <!-- Load More Button End -->
                    <div  style="padding-top: 0px; padding-bottom: 1px; position: static; transform: none; margin-top: 20px;">
                    <div class="main--content-inner drop--shadow">
                            <!-- Filter Nav Start -->
                            <div class="filter--nav pb--60 clearfix">
                                <div class="filter--link float--left">
                                    <h2 class="h4"><a href="/activity" class="btn-link">Activities</a></h2>
                                </div>

                            </div>
                            <!-- Filter Nav End -->

                            <!-- Activity List Start -->
                            <div class="activity--list">
                                <!-- Activity Items Start -->
                                <ul class="activity--items">
                            
                                   
                                </ul>
                                <!-- Activity Items End -->
                            </div>
                            <!-- Activity List End -->
                        </div><div class="load-more--btn pt--30 text-center">
                            <a href="#" class="btn btn-animate">
                                <span>See More Activities<i class="fa ml--10 fa-caret-right"></i></span>
                            </a>
                        </div><div class="resize-sensor" style="position: absolute; left: 0px; top: 0px; right: 0px; bottom: 0px; overflow: hidden; z-index: -1; visibility: hidden;"><div class="resize-sensor-expand" style="position: absolute; left: 0; top: 0; right: 0; bottom: 0; overflow: hidden; z-index: -1; visibility: hidden;"><div style="position: absolute; left: 0px; top: 0px; transition: all 0s ease 0s; width: 790px; height: 2751px;"></div></div><div class="resize-sensor-shrink" style="position: absolute; left: 0; top: 0; right: 0; bottom: 0; overflow: hidden; z-index: -1; visibility: hidden;"><div style="position: absolute; left: 0; top: 0; transition: 0s; width: 200%; height: 200%"></div></div></div></div></div>
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
                    <div class="theiaStickySidebar" style="padding-top: 0px; padding-bottom: 1px; position: static; transform: none; top: 0px; left: 1162.5px;">
                    		<div class="widget">
                            <h2 class="h4 fw--700 widget--title">Search</h2>

	                        </div>
							
                        
                        <div class="resize-sensor" style="position: absolute; left: 0px; top: 0px; right: 0px; bottom: 0px; overflow: hidden; z-index: -1; visibility: hidden;"><div class="resize-sensor-expand" style="position: absolute; left: 0; top: 0; right: 0; bottom: 0; overflow: hidden; z-index: -1; visibility: hidden;"><div style="position: absolute; left: 0px; top: 0px; transition: all 0s ease 0s; width: 400px; height: 1360px;"></div></div><div class="resize-sensor-shrink" style="position: absolute; left: 0; top: 0; right: 0; bottom: 0; overflow: hidden; z-index: -1; visibility: hidden;"><div style="position: absolute; left: 0; top: 0; transition: 0s; width: 200%; height: 200%"></div></div></div></div></div>
                    <!-- Main Sidebar End -->
                </div>
            </div>
        </section>

</c:if>