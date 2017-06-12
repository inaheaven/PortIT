<%@ page contentType="text/html; charset=EUC-KR"%>
<jsp:useBean id="profile" class="portit.model.dto.Profile" scope="session"/>
<jsp:useBean id="tag" class="portit.model.dto.Tag" scope="session"/>
<jsp:useBean id="portfolio" class="portit.model.dto.Portfolio" scope="session"/>
<jsp:useBean id="project" class="portit.model.dto.Project" scope="session"/>

<jsp:getProperty property="prof_name" name="profile"/>
<jsp:getProperty property="pf_title" name="portfolio"/>
<jsp:getProperty property="prof_name" name="profile"/>
<jsp:getProperty property="tag_name" name="tag"/>

