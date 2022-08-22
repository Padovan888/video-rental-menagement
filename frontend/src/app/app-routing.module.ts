import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'atores',
    loadChildren: () => import('./modules/actor/actor.module').then(m => m.ActorModule)
  },
  {
    path: 'diretores',
    loadChildren: () => import('./modules/director/director.module').then(m => m.DirectorModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
