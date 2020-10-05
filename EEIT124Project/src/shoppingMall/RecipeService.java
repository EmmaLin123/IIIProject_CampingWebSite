package shoppingMall;

import java.util.List;

public interface RecipeService {

	List<String> getCategory();
	
	String getCategoryTag();
	
	List<RecipeBean> getPageRecipe();
	
	int getPageNo();
	
	int getRecordsPerPage();

	long getRecordCounts();
	
	int getTotalPages();
	
	

}
