import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AlumnosComponent } from './components/alumnos/alumnos.component';
import { CursosComponent } from './components/cursos/cursos.component';
import { ExamenesComponent } from './components/examenes/examenes.component';
import { AlumnosFormComponent } from './components/alumnos/alumnos-form.component';
import { CursosFormComponent } from './components/cursos/cursos-form.component';
import { ExamenesFormComponent } from './components/examenes/examenes-form.component';
import { AsignarAlumnosComponent } from './components/cursos/asignar-alumnos.component';

const routes: Routes = [
    {path:'', pathMatch:'full', redirectTo:'cursos'},
    {path:'alumnos', component: AlumnosComponent},
    {path:'alumnos/form', component: AlumnosFormComponent},
    {path:'alumnos/form/:id', component: AlumnosFormComponent},
    {path:'cursos', component: CursosComponent},
    {path:'cursos/form', component: CursosFormComponent},
    {path:'cursos/form/:id', component: CursosFormComponent},
    {path:'cursos/asignar-alumnos/:id', component: AsignarAlumnosComponent},
    {path:'examenes', component: ExamenesComponent},
    {path:'examenes/form', component: ExamenesFormComponent},
    {path:'examenes/form/:id', component: ExamenesFormComponent},
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {}