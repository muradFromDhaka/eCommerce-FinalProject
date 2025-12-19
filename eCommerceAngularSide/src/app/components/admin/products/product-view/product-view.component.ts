import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductDetailsDto } from 'src/app/model/product.model';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-view',
  templateUrl: './product-view.component.html',
  styleUrls: ['./product-view.component.scss']
})
export class ProductViewComponent {
productDetails!: ProductDetailsDto;
  mainImage!: string;
  id!: string | null;

  constructor(
    private router: Router,
    private acRouter: ActivatedRoute,
    private productService: ProductService
  ) {}

  ngOnInit() {
    this.id = this.acRouter.snapshot.paramMap.get('id');
    this.productService.getById(Number(this.id))
      .subscribe(product => {
        this.productDetails = product;

        // Set main image to the first image or placeholder if none
        if (product.imageUrls && product.imageUrls.length > 0) {
          this.mainImage = product.imageUrls[0];
        } else {
          this.mainImage = 'assets/img/product-placeholder.png';
        }
      });
  }

  setMainImage(img: string) {
    this.mainImage = img;
  }

 

}
