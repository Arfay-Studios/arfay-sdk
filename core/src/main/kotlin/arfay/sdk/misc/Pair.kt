/*
Copyright (C) 2022 Arfay

You may not use this file except in compliance with the Team Agreement.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*/

package arfay.sdk.misc

class Pair<L, R>(
    val left: L,
    val right: R
) {
    override fun equals(other: Any?): Boolean {
        if (other === null) return false

        if (this === other) return true

        if (javaClass != other.javaClass) return false

        other as Pair<*, *>

        if (left != other.left || right != other.right) return false

        return true
    }

    override fun hashCode(): Int {
        return left.hashCode() + right.hashCode()
    }
}
