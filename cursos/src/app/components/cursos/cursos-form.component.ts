import { Component, OnInit } from '@angular/core';
import { CommonFormComponent } from '../common-form';
import { Curso } from 'src/app/models/curso';
import { CursoService } from 'src/app/services/curso.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-cursos-form',
  templateUrl: './cursos-form.component.html',
  styleUrls: ['./cursos-form.component.css']
})
export class CursosFormComponent extends CommonFormComponent<Curso, CursoService>{

  entity: Curso = new Curso();
  protected nombreModel: string = "Curso";
  protected rutaRedirect: string = "/cursos";

  constructor(cursoService: CursoService, router: Router, activatedRoute: ActivatedRoute) {
    super(cursoService, router, activatedRoute);
   }

}
