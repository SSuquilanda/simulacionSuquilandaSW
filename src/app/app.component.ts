import { Component } from '@angular/core';
import { ConsultasService } from './servicios/consultas.service';
import { RouterOutlet } from '@angular/router';
import { FormsModule } from '@angular/forms'; // Importa FormsModule
import { CommonModule } from '@angular/common'; // Para *ngFor y otras directivas comunes

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, FormsModule, CommonModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'Frontend';
  cedula: string = '';
  usuario: any = null;
  consumos: any[] = [];
  errorMessage: string = '';

  constructor(private consultaService: ConsultasService) {}

  consultar() {
    if (!this.cedula) {
      this.errorMessage = 'Ingrese una cédula válida';
      return;
    }

    this.consultaService.getDatosCompletos(this.cedula).subscribe({
      next: (data) => {
        this.usuario = data.usuario;
        this.consumos = data.consumos;
        this.errorMessage = '';
      },
      error: (error) => {
        this.errorMessage = 'Usuario no encontrado o sin consumos registrados';
        this.usuario = null;
        this.consumos = [];
      },
    });
  }
}
