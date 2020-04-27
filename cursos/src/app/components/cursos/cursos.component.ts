import { Component, OnInit } from '@angular/core';
import { CommonListarComponent } from '../common-listar';
import { CursoService } from 'src/app/services/curso.service';
import { Curso } from 'src/app/models/curso';

@Component({
  selector: 'app-cursos',
  templateUrl: './cursos.component.html',
  styleUrls: ['./cursos.component.css']
})
export class CursosComponent extends CommonListarComponent<Curso, CursoService> {

  constructor(cursoService: CursoService) {
    super(cursoService);
    this.titulo = "Listado de Cursos";
    this.nombreModel="Curso";
  }

}
