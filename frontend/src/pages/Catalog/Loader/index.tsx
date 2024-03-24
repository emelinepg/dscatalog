import ContentLoader from 'react-content-loader';
import './styles.css'

const Loader = () => (
  <div className="loader-container">
    <ContentLoader
      speed={1}
      width={400}
      height={160}
      viewBox="0 0 400 160"
      backgroundColor="#f3f3f3"
      foregroundColor="#acabab"
    >
      <rect x="68" y="58" rx="3" ry="3" width="49" height="6" />
      <rect x="66" y="7" rx="0" ry="0" width="53" height="46" />
      <rect x="68" y="68" rx="3" ry="3" width="49" height="6" />
      <rect x="135" y="58" rx="3" ry="3" width="49" height="6" />
      <rect x="133" y="7" rx="0" ry="0" width="53" height="46" />
      <rect x="135" y="68" rx="3" ry="3" width="49" height="6" />
      <rect x="200" y="59" rx="3" ry="3" width="49" height="6" />
      <rect x="198" y="8" rx="0" ry="0" width="53" height="46" />
      <rect x="200" y="69" rx="3" ry="3" width="49" height="6" />
      <rect x="267" y="59" rx="3" ry="3" width="49" height="6" />
      <rect x="265" y="8" rx="0" ry="0" width="53" height="46" />
      <rect x="267" y="69" rx="3" ry="3" width="49" height="6" />
      <rect x="69" y="137" rx="3" ry="3" width="49" height="6" />
      <rect x="67" y="86" rx="0" ry="0" width="53" height="46" />
      <rect x="69" y="147" rx="3" ry="3" width="49" height="6" />
      <rect x="136" y="137" rx="3" ry="3" width="49" height="6" />
      <rect x="134" y="86" rx="0" ry="0" width="53" height="46" />
      <rect x="136" y="147" rx="3" ry="3" width="49" height="6" />
      <rect x="201" y="138" rx="3" ry="3" width="49" height="6" />
      <rect x="199" y="87" rx="0" ry="0" width="53" height="46" />
      <rect x="201" y="148" rx="3" ry="3" width="49" height="6" />
      <rect x="268" y="138" rx="3" ry="3" width="49" height="6" />
      <rect x="266" y="87" rx="0" ry="0" width="53" height="46" />
      <rect x="268" y="148" rx="3" ry="3" width="49" height="6" />
    </ContentLoader>
  </div>
);

export default Loader;
