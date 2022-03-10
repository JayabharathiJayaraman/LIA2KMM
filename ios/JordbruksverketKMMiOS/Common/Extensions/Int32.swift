import shared

extension Int32 {
    var asKotlinInt: KotlinInt {
        KotlinInt(int: self)
    }
}
