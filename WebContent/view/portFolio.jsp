<%@ page contentType="text/html; charset=EUC-KR"%>
<jsp:useBean id="profile" class="portit.model.dto.Profile" scope="session"/>
<jsp:useBean id="tag" class="portit.model.dto.Tag" scope="session"/>
<jsp:useBean id="portfolio" class="portit.model.dto.Portfolio" scope="session"/>
<jsp:useBean id="media" class="portit.model.dto.Media" scope="session"/>

<jsp:getProperty property="ml_path" name="media"/>
<jsp:getProperty property="tag_name" name="tag"/>
<jsp:getProperty property="pf_title" name="protfolio"/>
<jsp:getProperty property="prof_name" name="profile"/>
<jsp:getProperty property="pf_like" name="protfolio"/>

