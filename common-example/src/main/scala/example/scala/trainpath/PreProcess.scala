/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package example.scala.trainpath

import java.io.{BufferedWriter, FileWriter}

import scala.io.Source

object PreProcess {
  def main(args: Array[String]): Unit = {
    val pat_node = """StationNode\s+(\w+)\s+=\s+new\s+StationNode\("(\w+)", (-?\d+(?:\.\d+)?), (-?\d+(?:\.\d+)?)\);""".r
    val pat_connect = """StationGraph.AddEdge\((\w+),\s*(\w+)\);""".r
    val node_lst = """d:\stations\nodes.lst"""
    val connect_lst = """d:\stations\connects.lst"""
    val station_node_lst = """d:\stations\station_nodes.lst"""
    val station_connect_lst = """d:\stations\station_connects.lst"""

    val bwNode = new BufferedWriter(new FileWriter(station_node_lst))
    val bwCon = new BufferedWriter(new FileWriter(station_connect_lst))

    Source.fromFile(node_lst).getLines().map(_.trim).foreach {line =>
      val pat_node(varname, name, xloc, yloc) = line
      val node_type = if (name.contains("switch")) 1 else if (name.contains("sig")) 2 else 0
      bwNode.write(s"$varname,$name,$xloc,$yloc,$node_type\n")
    }

    Source.fromFile(connect_lst).getLines().map(_.trim).foreach {line =>
      val pat_connect(v1, v2) = line
      bwCon.write(s"$v1,$v2\n")
    }

    bwNode.close()
    bwCon.close()
  }
}
