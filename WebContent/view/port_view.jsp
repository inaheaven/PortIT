<%@ page contentType="text/html; charset=EUC-KR"%>
<jsp:useBean id="media" class="portit.model.dto.Media" />
<jsp:useBean id="tag" class="portit.model.dto.Tag" />
<jsp:useBean id="portfolio" class="portit.model.dto.Portfolio" />
<jsp:useBean id="profile" class="portit.model.dto.Profile" />

<jsp:getProperty name="media" property="ml_path"/>
<jsp:getProperty name="tag" property="tag_name"/>
<jsp:getProperty name="portfolio" property="pf_title"/>
<jsp:getProperty name="profile" property="prof_name"/>
<jsp:getProperty name="portfolio" property="pf_like"/>