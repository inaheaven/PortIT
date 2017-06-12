<%@ page contentType="text/html; charset=EUC-KR"%>
<jsp:useBean id="profile" class="portit.model.dto.Profile" scope="session"/>
<jsp:useBean id="tag" class="portit.model.dto.Tag" scope="session"/>

<jsp:getProperty property="prof_img" name="profile"/>
<jsp:getProperty property="prof_name" name="profile"/>
<jsp:getProperty property="tag_name" name="tag"/>
<jsp:getProperty property="prof_follower" name="profile"/>