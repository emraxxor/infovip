<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/tlds/module-manager" prefix="module"%>
<%@ taglib uri="/tlds/translate" prefix="tr"%>


<header class="header--section style--3">
            <!-- Header Topbar Start -->
            <c:if test="${not empty isAuthenticated && isAuthenticated == true}">
            <div class="header--topbar bg-dark">
                <div class="container">
                
                    <!-- Header Topbar Links Start -->
                    <ul class="header--topbar-links nav ff--primary no--stripes float--left">
                        <li><a href="#">Settings</a></li>
                        <li><a href="/activity">Activity</a></li>
                        <li><a href="#">Find Friends</a></li>
                    </ul>
                    <!-- Header Topbar Links End -->

                    <!-- Header Topbar Links Start -->
                    <ul class="header--topbar-links nav ff--primary float--right">
                        <li>
                            <a href="#" title="" data-toggle="tooltip" data-placement="bottom" data-original-title="Messages">
                                <i class="fa fa-comment"></i>
                                <span class="badge">3</span>
                            </a>
                        </li>
                        <li>
                            <a href="#" class="btn-link">
                                <i class="fa fa-user-o"></i>
                                <span>Profile</span>
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.servletContext.contextPath}/logout" class="btn-link">
                                <i class="fa fa-sign-out"></i>
                                <span>Sign out</span>
                            </a>
                        </li>
                    </ul>
                    <!-- Header Topbar Links End -->
                </div>
            </div>
		    </c:if>
            <!-- Header Topbar End -->

            <!-- Header Navbar Start -->
            <div id="sticky-wrapper" class="sticky-wrapper" style="height: 80px;"><div class="header--navbar navbar bg-default" data-trigger="sticky" style="">
                <div class="container">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle style--3 collapsed" data-toggle="collapse" data-target="#headerNav">
                            <span class="sr-only">Toggle Navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>

                        <!-- Header Navbar Logo Start -->
                        <div class="header--navbar-logo navbar-brand">
                            <a href="index.html">
                                <img src="${resources}/img/logo-black.png" alt="">
                            </a>
                        </div>
                        <!-- Header Navbar Logo End -->
                    </div>

                    <div id="headerNav" class="navbar-collapse collapse float--right">
                        <!-- Header Nav Links Start -->
                        <ul class="header--nav-links style--3 nav ff--primary">
                            <li class="dropdown active">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <span>Home</span>
                                    <i class="fa fa-caret-down"></i>
                                </a>

                                <ul class="dropdown-menu">
                                    <li><a href="/"><span>Home</span></a></li>
                                    <li><a href="/login"><span>Login</span></a></li>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <span>Community</span>
                                    <i class="fa fa-caret-down"></i>
                                </a>

                                <ul class="dropdown-menu">
                                    <li><a href="/activity"><span>Activity</span></a></li>
                                    <li><a href="/members"><span>Members</span></a></li>
                                    <li><a href="/groups"><span>Groups</span></a></li>
                                    <li><a href="/gallery"><span>Groups</span></a></li>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <span>Products</span>
                                    <i class="fa fa-caret-down"></i>
                                </a>

                                <ul class="dropdown-menu">
	                                <li><a href="/product-categories">Product categories</a></li>
                                    <li><a href="/products">Products</a></li>
                                </ul>
                            </li>
                            <li><a href="/gallery"><span>Gallery</span></a></li>
                            <li><a href="/contact"><span>Contact</span></a></li>
                        </ul>
                        <!-- Header Nav Links End -->
                    </div>
                </div>
            </div></div>
            <!-- Header Navbar End -->
        </header>