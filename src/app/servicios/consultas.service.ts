import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class ConsultasService {
  private baseUrl = 'http://localhost:8080/simulacionSuquilanda-1.0-SNAPSHOT/api'; // URL base del backend

  constructor(private http: HttpClient) {}

  // Método para obtener clientes por cédula
  getClientes(cedula: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/usuarios/${cedula}`).pipe(
      catchError(this.handleError)
    );
  }

  // Método para obtener consumos por cédula
  getConsumos(cedula: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/consumos/${cedula}`).pipe(
      catchError(this.handleError)
    );
  }
  // Método para obtener usuario y consumos
  getDatosCompletos(cedula: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/consulta/${cedula}`).pipe(
      catchError(this.handleError)
    );
  }

  // Manejo de errores
  private handleError(error: HttpErrorResponse): Observable<never> {
    let errorMessage = 'Ocurrió un error desconocido';
    if (error.error instanceof ErrorEvent) {
      // Error del cliente o de red
      errorMessage = `Error del cliente o red: ${error.error.message}`;
    } else {
      // Error del servidor
      errorMessage = `Error del servidor ${error.status}: ${error.message}`;
    }
    console.error(errorMessage); // Mostrar el error en la consola
    return throwError(() => new Error(errorMessage)); // Lanza un error para manejarlo en el componente
  }
}
