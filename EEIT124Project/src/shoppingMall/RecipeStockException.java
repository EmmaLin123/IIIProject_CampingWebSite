package shoppingMall;

public class RecipeStockException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public RecipeStockException() {
		super();
	}
	
	public RecipeStockException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	public RecipeStockException(String message, Throwable cause) {
		super(message, cause);
	}

	public RecipeStockException(String message) {
		super(message);
	}
	
	public RecipeStockException(Throwable cause) {
		super(cause);
	}
	
}
