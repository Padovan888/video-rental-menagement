import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'login',
    loadChildren: () => import('./modules/login/login.module').then(m => m.LoginModule)
  },
  {
    path: 'atores',
    loadChildren: () => import('./modules/actor/actor.module').then(m => m.ActorModule)
  },
  {
    path: 'diretores',
    loadChildren: () => import('./modules/director/director.module').then(m => m.DirectorModule)
  },
  {
    path: 'classes',
    loadChildren: () => import('./modules/class/class.module').then(m => m.ClassModule)
  },  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
