/*
 * Copyright 2014 websudos ltd.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.websudos.reactiveneo.dsl

import com.websudos.reactiveneo.attribute.Attribute
import com.websudos.reactiveneo.query.{CypherOperators, BuiltQuery}
import play.api.libs.json.{Reads, _}


trait Functions {

  /**
   * Functions that can be applied in `match` or `return` clauses.
   */
  abstract class Function[T] extends ReturnExpression[T]

  class Count extends Function[Int] {

    override def query( context: QueryBuilderContext ): BuiltQuery = "count(*)"

    override def resultParser: Reads[Int] = (__ \ "count(*)").read[Int]

  }


  class CountOfNodes(node: Node[_,_]) extends Function[Int] {

    override def query( context: QueryBuilderContext ): BuiltQuery = {
      val label = context.resolve(node)
      s"count($label)"
    }

    override def resultParser: Reads[Int] = (__ \ "count(*)").read[Int]

  }

  class CountOfValues(attr: Attribute[_,_,_]) extends Function[Int] {

    override def query( context: QueryBuilderContext ): BuiltQuery = {
      val label =     context.resolve(attr.owner.asInstanceOf[GraphObject[_,_]]) + CypherOperators.DOT + attr.name
      s"count($label)"
    }

    override def resultParser: Reads[Int] = (__ \ "count(*)").read[Int]

  }

  /**
   * `count` function initializations.
   */
  object count {

    def apply() = new Count

    def apply(node: Node[_, _]) = new CountOfNodes(node)

    def apply(attr: Attribute[_,_,_]) = new CountOfValues(attr)

  }



}