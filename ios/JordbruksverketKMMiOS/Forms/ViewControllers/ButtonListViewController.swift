import UIKit

final class ButtonListViewController: UIViewController {
    typealias ItemSelectionHandler = (String) -> Void

    var itemSelectionHandler: ItemSelectionHandler?

    private let list: [String]

    @IBOutlet private var tableView: UITableView!

    init(list: [String]) {
        self.list = list
        super.init(nibName: String(describing: ButtonListViewController.self), bundle: nil)
    }

    required init?(coder: NSCoder) { nil }

    override func viewDidLoad() {
        super.viewDidLoad()

        tableView.delegate = self
        tableView.dataSource = self
        tableView.register(UITableViewCell.self, forCellReuseIdentifier: String(describing: UITableViewCell.self))
    }
}

extension ButtonListViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let item = list[indexPath.row]
        itemSelectionHandler?(item)
    }
}

extension ButtonListViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        list.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: String(describing: UITableViewCell.self), for: indexPath)
        let item = list[indexPath.row]
        cell.textLabel?.text = item
        cell.textLabel?.font = .scaledFont(name: UIFont.fontNameRegular, textStyle: .body)
        cell.textLabel?.textColor = .Jordbruksverket.defaultTextColor
        cell.textLabel?.adjustsFontForContentSizeCategory = true
        return cell
    }
}
