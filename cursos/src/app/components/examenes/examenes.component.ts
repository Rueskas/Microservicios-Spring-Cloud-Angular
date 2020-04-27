import { Component, OnInit } from '@angular/core';
import { CommonListarComponent } from '../common-listar';
import { Examen } from 'src/app/models/examen';
import { ExamenService } from 'src/app/services/examen.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-examenes',
  templateUrl: './examenes.component.html',
  styleUrls: ['./examenes.component.css']
})
export class ExamenesComponent extends CommonListarComponent<Examen, ExamenService> implements OnInit {

  constructor(service: ExamenService, router: Router, activatedRoute: ActivatedRoute) {
    super(service);
    this.nombreModel = "Examen";
    this.titulo = "Listado de Examenes";
  }

}
