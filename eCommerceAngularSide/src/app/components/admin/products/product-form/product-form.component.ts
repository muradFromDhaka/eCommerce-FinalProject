import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Category } from 'src/app/model/category.model';
import { ProductRequest } from 'src/app/model/product.model';
import { CategoryService } from 'src/app/services/category.service';
import { ProductService } from 'src/app/services/product.service';
@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.scss'],
})
export class ProductFormComponent implements OnInit {
  productForm!: FormGroup;
  id!: string | null;
  categories: Category[] = [];

  constructor(
    private fb: FormBuilder,
    private productService: ProductService,
    private categoryService: CategoryService,
    private router: Router,
    private acRouter: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.loadCategories();
    this.loadProduct();
  }

  loadCategories() {
    this.categoryService
      .getAllCategories()
      .subscribe((category) => (this.categories = category));
  }

  loadProduct() {
    this.productForm = this.fb.group({
      name: ['', [Validators.required]],
      description: ['', [Validators.required]],
      price: [null, [Validators.required]],
      stockQuantity: [0],
      available: [false],
      releaseDate: [],
      categoryId: [null],
      imageUrls: [''],
    });

    this.id = this.acRouter.snapshot.paramMap.get('id');
    if (this.id) {
      this.productService.getById(+this.id).subscribe((product) =>
        this.productForm.patchValue({
          name: product.name,
          description: product.description,
          price: product.price,
          stockQuantity: product.stockQuantity,
          available: product.available,
          releaseDate: product.releaseDate ?? '',
          categoryId: product.categoryId ?? null,
          imageUrls: product.imageUrls ? product.imageUrls.join(',') : '',
        })
      );
    }
  }

  save() {
  if (this.productForm.invalid) {
    this.productForm.markAllAsTouched();
    return;
  }

  const formValue = this.productForm.value;

  const product: ProductRequest = {
    name: formValue.name,
    description: formValue.description,
    price: formValue.price,
    stockQuantity: formValue.stockQuantity,
    available: formValue.available,

    releaseDate: formValue.releaseDate
      ? new Date(formValue.releaseDate).toISOString().split('T')[0]
      : undefined,

    categoryId: Number(formValue.categoryId),
    
    imageUrls: formValue.imageUrls
      ? formValue.imageUrls.split(',').map((url: string) => url.trim())
      : [],
  };

  if (this.id) {
    this.productService
      .update(+this.id, product)
      .subscribe(() => this.router.navigate(['/admin/adminProductList']));
  } else {
    this.productService
      .create(product)
      .subscribe(() => this.router.navigate(['/admin/adminProductList']));
  }
}

  onSubmit() {
  if (this.productForm.valid) {
    this.save();
    this.productForm.reset();
  } else {
    this.productForm.markAllAsTouched();
  }
}
}
