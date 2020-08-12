<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/tlds/module-manager" prefix="module"%>
<%@ taglib uri="/tlds/translate" prefix="tr"%>


<tiles:insertTemplate template="${Configuration.WEB_DIRECTORY}/user/header/header.jsp"></tiles:insertTemplate>  			
  
 <section class="page--wrapper pt--80 pb--20" style="transform: none;">
            <div class="container" style="transform: none;">
                <div class="row" style="transform: none;">
                

                 <div class="main--content col-md-8 pb--60">
                 	<tiles:insertAttribute name="MAIN_CONTENT" flush="true"></tiles:insertAttribute>
                 </div>
                 
                 <div class="main--sidebar col-md-4 pb--60">
                 	<tiles:insertTemplate template="${Configuration.WEB_DIRECTORY}/user/menu/DefaultSideBarMenu.jsp"></tiles:insertTemplate>  			
                 </div>     
                 
                 </div>
            </div>
</section>
                 