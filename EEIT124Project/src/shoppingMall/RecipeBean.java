package shoppingMall;

public class RecipeBean {
	
	String reid;
    String rename;
    String brief;
    String image;
    String ingredient;
    String tip1;
    String tip2;
    String tip3;
    String tip4;
    String tip5;
    String tip6;
    String note;
    int people;
    int time;
    
    public RecipeBean() {
    	
    }
    
    public RecipeBean(String reid, String rename, String brief, String image, int people, int time) {
    	this.reid = reid;
    	this.rename = rename;
    	this.brief = brief;
    	this.image = image;
    	this.people = people;
    	this.time = time;
    }
    
public RecipeBean(String reid,String rename,String brief,String image,String ingredient,String tip1,String tip2,String tip3,String tip4,String tip5,
		String tip6,String note,int people,int time) 
{
	this.reid = reid;
	this.rename = rename;
	this.brief = brief;
	this.image = image;
	this.ingredient = ingredient;
	this.tip1 = tip1;
	this.tip2 = tip2;
	this.tip3 = tip3;
	this.tip4 = tip4;
	this.tip5 = tip5;
	this.tip6 = tip6;
	this.note = note;
	this.people = people;
	this.time = time;	
    }



public RecipeBean(String reid) {
	this.reid = reid;
}

public String getReid() {
	return reid;
}

public void setReid(String reid) {
	this.reid = reid;
}

public String getRename() {
	return rename;
}

public void setRename(String rename) {
	this.rename = rename;
}

public String getBrief() {
	return brief;
}

public void setBrief(String brief) {
	this.brief = brief;
}

public String getImage() {
	return image;
}

public void setImage(String image) {
	this.image = image;
}

public String getTip1() {
	return tip1;
}

public void setTip1(String tip1) {
	this.tip1 = tip1;
}

public String getTip2() {
	return tip2;
}

public void setTip2(String tip2) {
	this.tip2 = tip2;
}

public String getTip3() {
	return tip3;
}

public void setTip3(String tip3) {
	this.tip3 = tip3;
}

public String getTip4() {
	return tip4;
}

public void setTip4(String tip4) {
	this.tip4 = tip4;
}

public String getTip5() {
	return tip5;
}

public void setTip5(String tip5) {
	this.tip5 = tip5;
}

public String getTip6() {
	return tip6;
}

public void setTip6(String tip6) {
	this.tip6 = tip6;
}

public String getNote() {
	return note;
}

public void setNote(String note) {
	this.note = note;
}

public int getPeople() {
	return people;
}

public void setPeople(int people) {
	this.people = people;
}

public int getTime() {
	return time;
}

public void setTime(int time) {
	this.time = time;
}

public String getIngredient() {
	return ingredient;
}

public void setIngredient(String ingredient) {
	this.ingredient = ingredient;
}


}


