<%@ page contentType="text/html; charset=EUC-KR"%>
<jsp:useBean id="profile" class="portit.model.dto.Profile" scope="session"/>
<jsp:useBean id="tag" class="portit.model.dto.Tag" scope="session"/>
<jsp:useBean id="portfolio" class="portit.model.dto.Portfolio" scope="session"/>
<jsp:useBean id="project" class="portit.model.dto.Project" scope="session"/>

<jsp:getProperty property="tag_name" name="tag"/>
<jsp:getProperty property="proj_title" name="project"/>
<jsp:getProperty property="proj_to" name="project"/>
<jsp:getProperty property="proj_regenddate" name="project"/>

