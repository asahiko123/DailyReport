package com.example.demo.app.supplier;

public class SupplierForm {
	
	private int id;
	private int typeId;
	private String name;
	private boolean newSupplier;
	
	public SupplierForm() {
		
	}
	
	public SupplierForm(int id, int typeId, String name, boolean newSupplier) {
		this.id = id;
		this.typeId = typeId;
		this.name  = name;
		this.newSupplier = newSupplier;
	}

	public boolean isNewSupplier() {
		return newSupplier;
	}

	public void setNewSupplier(boolean newSupplier) {
		this.newSupplier = newSupplier;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int supplierId) {
		this.typeId = supplierId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
