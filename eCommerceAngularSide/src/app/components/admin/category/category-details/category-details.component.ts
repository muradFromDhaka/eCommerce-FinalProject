import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Category } from 'src/app/model/category.model';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-category-details',
  templateUrl: './category-details.component.html',
  styleUrls: ['./category-details.component.scss']
})
export class CategoryDetailsComponent {

  category: Category | null = null;
  loading: boolean = true;
  error: string | null = null;
  categoryId!: number;

  constructor(
    private route: ActivatedRoute,
    private categoryService: CategoryService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.categoryId = +params['id'];
      this.loadCategoryDetails();
    });
  }

  loadCategoryDetails(): void {
    this.loading = true;
    this.error = null;
    
    this.categoryService.getCategoryById(this.categoryId).subscribe({
      next: (data) => {
        this.category = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Category not found or failed to load.';
        this.loading = false;
        console.error('Error loading category:', err);
      }
    });
  }

  onImageError(event: Event): void {
    const img = event.target as HTMLImageElement;
    img.src = 'assets/default-category.jpg';
  }

}
