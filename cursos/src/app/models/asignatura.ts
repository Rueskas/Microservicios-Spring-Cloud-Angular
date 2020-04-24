export class Asignatura {
    id: number;
    nombre: string;
    hijos: Asignatura[] = [];
    padre: Asignatura;
}
