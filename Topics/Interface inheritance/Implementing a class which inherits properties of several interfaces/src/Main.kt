interface IMovable {
    val externalEffect: Boolean
    val selfEffect: Boolean
}

interface IMassive {
    val mass: Int
    var massMultiplier: Int
}

interface IControllable {
    var currentlyControlled: Boolean
    var controllers: Int
}

interface ISimulated : IMovable, IMassive {
    var isSimulating: Boolean
}

interface Entity : ISimulated {
    val entityId: Int
}

// Do not change the code above!

class Player//Your code here
    (
    override val externalEffect: Boolean,
    override val selfEffect: Boolean,
    override val mass: Int,
    override var massMultiplier: Int,
    override var currentlyControlled: Boolean,
    override var controllers: Int,
    override var isSimulating: Boolean,
    override val entityId: Int
) : Entity, IControllable