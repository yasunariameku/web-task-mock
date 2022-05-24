package entity;

public class Product {
	
	private Integer id;
	private Integer product_id;
	private Integer category_id;
	private String category;
	private String name;
	private Integer price;
	private String description;
	
	public Product() {
	}
	
	//登録するときに使ったコンストラクター
	public Product(Integer product_id, Integer category_id, String name, Integer price, String description) {
		this.product_id = product_id;
		this.category_id = category_id;
		this.name = name;
		this.price = price;
		this.description = description;
	}

	public Product(Integer id, Integer product_id,  String name, Integer price, String category, String description) {
		
		this.id = id;
	    this.product_id = product_id;
	    this.category = category;
	    this.name = name;
	    this.price = price;
	    this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	
	public Integer getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
