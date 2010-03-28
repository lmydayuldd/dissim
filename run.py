#!/usr/bin/python
# -*- coding: utf-8 -*-

    #Flood and evacuation simulator using multi-agent technology
    #Copyright (C) 2010 Alejandro Blanco and Manuel Gomar

    #This program is free software: you can redistribute it and/or modify
    #it under the terms of the GNU General Public License as published by
    #the Free Software Foundation, either version 3 of the License, or
    #(at your option) any later version.

    #This program is distributed in the hope that it will be useful,
    #but WITHOUT ANY WARRANTY; without even the implied warranty of
    #MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    #GNU General Public License for more details.

    #You should have received a copy of the GNU General Public License
    #along with this program.  If not, see <http://www.gnu.org/licenses/>.

import sys
import os

__jade = "java -cp '/usr/share/eclipse/plugins/it.fbk.sra.ejade_0.8.0/lib/libjade/*:/opt/lib/pfc/*:.' jade.Boot -gui -host klpt-chakra "
__creator = "God:agents.CreatorAgent"
__configPath = os.environ['HOME'] + "/.dissim/"
__scenPath = "./scen/"

# DEFINICIÓN DE FUNCIONES

def printUsage():
    print('Modo de empleo: ' + sys.argv[0] + ' [OPCIONES|FICHERO]')
    print('\nOpciones:')
    print('\t--help\tMuestra esta ayuda')
    print('\nSi no se le pasan argumentos se ejecutará el modo interactivo')
    print('para la generación de un nuevo escenario de simulación')

def launch(scen):
    os.system(__jade + __creator + "\\(" + scen + "\\)")

# MAIN

# Si no existe ya creamos el directorio de configuración
if not(os.access(__configPath, os.F_OK)):
    os.makedirs(__configPath)

if len(sys.argv) > 1:
    # No hay que generar un nuevo escenario
    op = sys.argv[1]
    if op.startswith('-'):
        # --help
        printUsage()
    else:
        # nos pasan el nombre del fichero con el escenario por parámetros
        launch(op)
else:
    # Generamos un nuevo escenario
    print('GENERANDO UN NUEVO ESCENARIO DE SIMULACIÓN\n')
    simName = raw_input('Nombre del nuevo escenario: ')
    scen = __scenPath + simName + '.scen'
    fich = open(scen, "w")
    fich.write('name=' + simName)
    # Preguntamos los datos del escenario
    desc = raw_input('Descripción del escenario: ')
    fich.write('description=' + desc)
    NW = raw_input('Coordenadas (con el formato Lat,Lng) NorOeste del área de simulación: ')
    NW = NW.split(',')
    fich.write('\nNW=[' + NW[0] + ',' + NW[1] + ']')
    SE = raw_input('Coordenadas (con el formato Lat,Lng) SudEste del área de simulación: ')
    SE = SE.split(',')
    fich.write('\nSE=[' + SE[0] + ',' + SE[1] + ']')
    tileSize = raw_input('Diámetro (en metros) de la circunferencia que circunscribe a los hexágonos: ')
    fich.write('\ntileSize=' + tileSize)
    nenvs = raw_input('Número de agentes entorno: ')
    fich.write('\nnumEnvs=' + nenvs)
    timeWS = raw_input('Tiempo (en milisegundos) entre entradas de nueva agua en la inundación: ')
    fich.write('\nupdateTimeWS=' + timeWS)
    nws = int(raw_input('Número de entradas de agua: '))
    for i in range(nws):
        ws = raw_input('Coordenadas (Lat,Lng) de la entrada de agua ' + str(i) + ': ')
        ws = tws.split(',')
        fich.write('\nwaterSource=[' + ws[0] + ',' + ws[1] + ',')
        wws = raw_input('Cantidad de agua de dicha entrada: ')
        fich.write(wws + ']')
    timePeople = raw_input('Tiempo (en milisegundos) entre actualizaciones de los agentes humanos')
    fich.write('\nupdateTimePeople=' + timePeople)
    # TODO
    fich.close()
    launch(scen)