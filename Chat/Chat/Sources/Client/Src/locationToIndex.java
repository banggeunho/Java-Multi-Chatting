package Chat.Sources.Client.Src;

public class locationToIndex {
	
	/* Translation Building name to defined Index (Hashing) */
   String Convert(String cur_loc, String des_loc) {
      String result="";
      /*Translation inserted current location by client to index*/
   if(cur_loc.contains("IT") || cur_loc.contains("아이티"))
      result +="0";
   if(cur_loc.contains("가천"))
      result +="1";
   if(cur_loc.contains("글로벌") ||cur_loc.contains("글센"))
      result +="2";
   if(cur_loc.startsWith("비전타워(학"))
      result +="3";
   if(cur_loc.startsWith("공과대학1") || cur_loc.startsWith("공대1") || cur_loc.startsWith("공학관"))
      result +="4";
   if(cur_loc.startsWith("비전타워(상"))
      result +="5";
   if(cur_loc.startsWith("공과대학2") || cur_loc.startsWith("공대2") || cur_loc.startsWith("창의관"))
      result +="6";
   if(cur_loc.contains("교육") || cur_loc.startsWith("교대"))
      result +="7";
   if(cur_loc.contains("중앙") || cur_loc.startsWith("중도"))
      result +="8";
   if(cur_loc.startsWith("예술대학1") || cur_loc.startsWith("예대1") || cur_loc.startsWith("창조관"))
      result +="9";
   if(cur_loc.startsWith("전자정보도서관") || cur_loc.startsWith("전자정보") ||cur_loc.startsWith("전정도"))
      result +="10";
   if(cur_loc.startsWith("예술대학2") || cur_loc.startsWith("예대2") || cur_loc.startsWith("예음관"))
      result +="11";
   if(cur_loc.startsWith("바이오나노대학") || cur_loc.startsWith("바나대") || cur_loc.startsWith("진리관"))
      result +="12";
   if(cur_loc.startsWith("바이오나노연구") || cur_loc.startsWith("바나연"))
      result +="13";
   if(cur_loc.startsWith("산학협력관") || cur_loc.startsWith("웅지관"))
      result +="14";
   if(cur_loc.startsWith("학생회관"))
      result +="15";
   if(cur_loc.startsWith("기숙사") || cur_loc.startsWith("긱사"))
      result +="16";
   
   /*Translation inserted destination location by client to index*/
   if(des_loc.contains("IT") || des_loc.contains("아이티"))
	      result +=",0";
	   if(des_loc.contains("가천"))
	      result +=",1";
	   if(des_loc.contains("글로벌") ||des_loc.contains("글센"))
	      result +=",2";
	   if(des_loc.startsWith("비전타워(학"))
	      result +=",3";
	   if(des_loc.startsWith("공과대학1") || des_loc.startsWith("공대1") || des_loc.startsWith("공학관"))
	      result +=",4";
	   if(des_loc.startsWith("비전타워(상"))
	      result +=",5";
	   if(des_loc.startsWith("공과대학2") || des_loc.startsWith("공대2") || des_loc.startsWith("창의관"))
	      result +=",6";
	   if(des_loc.contains("교육") || des_loc.startsWith("교대"))
	      result +=",7";
	   if(des_loc.contains("중앙") || des_loc.startsWith("중도"))
	      result +=",8";
	   if(des_loc.startsWith("예술대학1") || des_loc.startsWith("예대1") || des_loc.startsWith("창조관"))
	      result +=",9";
	   if(des_loc.startsWith("전자정보도서관") || des_loc.startsWith("전자정보") ||des_loc.startsWith("전정도"))
	      result +=",10";
	   if(des_loc.startsWith("예술대학2") || des_loc.startsWith("예대2") || des_loc.startsWith("예음관"))
	      result +=",11";
	   if(des_loc.startsWith("바이오나노대학") || des_loc.startsWith("바나대") || des_loc.startsWith("진리관"))
	      result +=",12";
	   if(des_loc.startsWith("바이오나노연구") || des_loc.startsWith("바나연"))
	      result +=",13";
	   if(des_loc.startsWith("산학협력관") || des_loc.startsWith("웅지관"))
	      result +=",14";
	   if(des_loc.startsWith("학생회관"))
	      result +=",15";
	   if(des_loc.startsWith("기숙사") || des_loc.startsWith("긱사"))
	      result +=",16";
   
   return result; 
   }
}