import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-category-form',
  templateUrl: './category-form.component.html',
  styleUrls: ['./category-form.component.scss']
})
export class CategoryFormComponent {
categoryForm!: FormGroup;
id!: string | null;

constructor(
  private fb: FormBuilder,
  private categoryService: CategoryService,
  private router: Router,
  private acRouter: ActivatedRoute
){}


ngOnInit(){
   this.loadCategory(); 
}

loadCategory(){
  this.categoryForm = this.fb.group({
    name:['', [Validators.required]],
    description:['', [Validators.required]],
    imageUrl:['', [Validators.required]],
  })

  this.id = this.acRouter.snapshot.paramMap.get('id');
  if(this.id){
    this.categoryService.getCategoryById(+this.id).subscribe((cate) => 
     this.categoryForm.patchValue({
        name: cate.name,
        description: cate.description,
        imageUrl: cate.imageUrl
     })
    )
  }
}

save(){
    const formValue = this.categoryForm.value;
    if(this.id){
      this.categoryService.updateCategory(+this.id, formValue)
          .subscribe(() => this.router.navigate(['/admin/adminCategoryList']))
    }else{
       this.categoryService.createCategory(formValue)
          .subscribe(() => this.router.navigate(['/admin/adminCategoryList']))
    }
  
}

onSubmit(){
  if(this.categoryForm.valid){
      this.save();
      this.categoryForm.reset();
  }else{
    this.categoryForm.markAllAsTouched();
  }
}

}
