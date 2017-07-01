package portit.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectApp_mem {
   
   /*
   select proj_app.mem_id, profile.prof_name,profile.prof_nick, proj_app.proj_app_confirm
   from proj_app inner join profile on proj_app.mem_id=profile.mem_id
   where proj_id=303;
    */
   
   
   private String mem_id;
   private String name;
   private String nick;
   private String app_confirm;
   public String getMem_id() {
      return mem_id;
   }
   public void setMem_id(String mem_id) {
      this.mem_id = mem_id;
   }
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public String getNick() {
      return nick;
   }
   public void setNick(String nick) {
      this.nick = nick;
   }
   public String getApp_confirm() {
      return app_confirm;
   }
   public void setApp_confirm(String app_confirm) {
      this.app_confirm = app_confirm;
   }
   
      
}