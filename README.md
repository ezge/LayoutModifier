Creating a custom layout modifier which can then be applied 
to any child element as needed. A custom layout modifier is 
passed a set of constraints indicating size restrictions 
and the child element to be positioned. The child can then 
be measured (an action that must only be performed once 
within a layout modifier) and calculations performed 
to customize the size and position of the child within 
the content area of the parent. Positioning may also be 
customized based on baseline alignment when supported 
by the child element.
