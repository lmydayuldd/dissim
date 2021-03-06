Disaster Simulator
##################

Disaster Simulator is a multi-agent system based on JADE technology.
It simulates floods and it can be easily extended to others disasters.
It generates kml files as a result of the simulation, but it would
be easy to develop new agents to generate different output files.

Usage
#####

Help (*./run.py --help*):

..code-block::

    Modo de empleo: run.py [OPCIONES] [FICHERO]

    Opciones:
            --help                          Muestra esta ayuda
            -h

            --add NOMBRE:CLASE PARAMETRO+   Añade un nuevo agente a una plataforma
            -a NOMBRE:CLASE PARAMETRO+

            --gui                           Lanza también la interfaz gráfica de JADE
            -g

            --name NOMBRE                   Permite establecer el nombre de la plataforma JADE (por defecto DisSim)
            -n NOMBRE

            --host HOST                     Permite establecer el host del contenedor principal
            -H HOST

            --port PUERTO                   Permite establecer el puerto a través del cual conectarse al contenedor principal
            -p PUERTO

            --local-host HOST               Permite establecer el host del contenedor
            -lh HOST

            --local-port PUERTO             Permite establecer el puerto a través del cual conectarse al contenedor
            -lp PUERTO

            --container                     Especifica que esta instancia de JADE es un contenedor
            -c

    Si no se le pasa ningún argumento se ejecutará el modo interactivo
    para la generación de un nuevo escenario de simulación

Final Year Project memory
#########################

https://github.com/ablanco/dissim-memoria

Legal
#####

This software is under a GPLv3 license.

    Flood and evacuation simulator using multi-agent technology
    Copyright (C) 2010-2011 Alejandro Blanco and Manuel Gomar

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

This software uses code from JCoord 1.0 project (http://www.jstott.me.uk/jcoord/), which is under a GPLv2 license.

    (c) 2006 Jonathan Stott
    Created on 11-02-2006

This software uses code from OpenWFE project (http://sourceforge.net/projects/openwfe/), which is under a Modified BSD license.

    Copyright (c) 2005-2006, John Mettraux, OpenWFE.org
    All rights reserved.

    Redistribution and use in source and binary forms, with or without
    modification, are permitted provided that the following conditions are met:

    . Redistributions of source code must retain the above copyright notice, this
    list of conditions and the following disclaimer.

    . Redistributions in binary form must reproduce the above copyright notice,
    this list of conditions and the following disclaimer in the documentation
    and/or other materials provided with the distribution.

    . Neither the name of the "OpenWFE" nor the names of its contributors may be
    used to endorse or promote products derived from this software without
    specific prior written permission.

    THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
    AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
    IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
    ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
    LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
    CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
    SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
    INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
    CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
    ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
    POSSIBILITY OF SUCH DAMAGE.

This software uses code from Java CSV project (http://sourceforge.net/projects/javacsv/), which is under a LGPLv2 license.

    Copyright (C) Bruce Dunwiddie bruce@csvreader.com
