import UIKit
import MapKit

class MapScreenViewController: UIViewController, CLLocationManagerDelegate {
    
    @IBOutlet weak var mapView: MKMapView!
    
    let locationManger = CLLocationManager()
    
    let regionInMeters: Double = 200
    
    override func viewDidLoad() {
        super.viewDidLoad()
        checkLocationServices()
        mapView.mapType = .satellite
    }

    @IBAction func infoButton(_ sender: UIButton) {
        
        let alert = UIAlertController(title: "Platsen för testet sparas i appen tillsammans med resultaten av de test du utfört. Dels som hjälp för minnet, för att du lättare ska kunna veta var du var. Dels för att du ska kunna återkomma till samma plats senare, för att göra nya test och följa upp dina åtgärder.", message: "", preferredStyle: UIAlertController.Style.alert)
        
        alert.addAction(UIAlertAction(title: "Stäng", style: UIAlertAction.Style.default, handler: nil))
        
        self.present(alert, animated: true, completion: nil)
    
    }
    
    
    override var shouldAutorotate: Bool{
        return false
    }
    
    override var supportedInterfaceOrientations: UIInterfaceOrientationMask{
        return .portrait
    }
    
    func mapView(_ mapView: MKMapView, viewFor annotation: MKAnnotation) -> MKAnnotationView? {
        
        guard !annotation.isKind(of: MKUserLocation.self) else { return nil }
        
        
        return MKAnnotationView()
    }
    
    func setupLocationManger() {
        locationManger.delegate = self
        locationManger.desiredAccuracy = kCLLocationAccuracyBest
    }
    
    func zoomInViewOnUserLocation () {
        if let location = locationManger.location?.coordinate {
            let region = MKCoordinateRegion.init(center: location, latitudinalMeters: regionInMeters, longitudinalMeters: regionInMeters)
            mapView.setRegion(region, animated: true)
    }
}
    
    func checkLocationServices() {
        if CLLocationManager.locationServicesEnabled(){
            setupLocationManger()
            checkLocationAuthorization()
            zoomInViewOnUserLocation()
            
        } else {
            
        }
    }
    
    func checkLocationAuthorization() {
        switch CLLocationManager.authorizationStatus() {
        case .authorizedWhenInUse:
            mapView.showsUserLocation = true
            locationManger.delegate = self
            locationManger.startUpdatingLocation()
            break
        case .denied:
            break
        case .notDetermined:
            locationManger.requestWhenInUseAuthorization()
        case .restricted:
            break
        case .authorizedAlways:
            break
        }
    }
    
    func locationManagerDidChangeAuthorization(_ manager: CLLocationManager) {
        checkLocationServices()
    }
}
